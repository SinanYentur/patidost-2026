package com.patidost.backend.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.patidost.backend.dto.AuthRequest
import com.patidost.backend.dto.AuthResponse
import com.patidost.backend.dto.OnboardingRequest
import com.patidost.backend.dto.SwipeCard
import com.patidost.backend.models.Pets
import com.patidost.backend.models.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import java.util.*

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }

fun Application.configureRouting() {

    val secret = environment.config.property("ktor.jwt.secret").getString()
    val issuer = environment.config.property("ktor.jwt.issuer").getString()
    val audience = environment.config.property("ktor.jwt.audience").getString()

    routing {
        get("/") {
            call.respondText("Hello from Patidost Backend!")
        }

        post("/register") {
            val authRequest = call.receive<AuthRequest>()
            val passwordHash = BCrypt.withDefaults().hashToString(12, authRequest.password.toCharArray())

            dbQuery {
                Users.insert {
                    it[id] = UUID.randomUUID().toString()
                    it[username] = authRequest.username
                    it[name] = authRequest.name ?: authRequest.username
                    it[Users.passwordHash] = passwordHash
                    it[profileImageUrl] = "/images/default_avatar.jpg"
                }
            }
            call.respond(HttpStatusCode.Created, "User registered successfully")
        }

        post("/login") {
            val authRequest = call.receive<AuthRequest>()
            val user = dbQuery {
                Users.selectAll().where { Users.username eq authRequest.username }.singleOrNull()
            }

            if (user == null) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
                return@post
            }

            val result = BCrypt.verifyer().verify(authRequest.password.toCharArray(), user[Users.passwordHash])
            if (result.verified) {
                val token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withClaim("username", user[Users.username])
                    .withClaim("userId", user[Users.id])
                    .withExpiresAt(Date(System.currentTimeMillis() + 60000000))
                    .sign(Algorithm.HMAC256(secret))
                call.respond(AuthResponse(token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
            }
        }

        authenticate("auth-jwt") {
            route("/user") {
                post("/onboard") {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()
                    val request = call.receive<OnboardingRequest>()

                    dbQuery {
                        Users.update({ Users.id eq userId }) {
                            it[name] = request.name
                            it[birthDate] = request.birthDate
                            it[hasPet] = request.hasPet
                        }
                    }
                    call.respond(HttpStatusCode.OK, "User details updated successfully")
                }
            }

            route("/home") {
                get("/swipe-feed") {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()

                    val swipeCards = dbQuery {
                        (Pets innerJoin Users)
                            .selectAll()
                            .where { Pets.ownerId neq userId } // Don't show user's own pet
                            .map { row ->
                                SwipeCard(
                                    petId = row[Pets.id],
                                    petName = row[Pets.name],
                                    petAge = row[Pets.age],
                                    petImageUrl = row[Pets.imageUrl],
                                    ownerId = row[Users.id],
                                    ownerName = row[Users.name],
                                    ownerProfileImageUrl = row[Users.profileImageUrl],
                                    distance = (1..15).random() // Placeholder distance until location is implemented
                                )
                            }
                    }
                    call.respond(swipeCards)
                }
            }
        }
    }
}
