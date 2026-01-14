package com.patidost.backend.services

import com.patidost.backend.models.PatiPuanTransactions
import com.patidost.backend.models.SubscriptionLevel
import com.patidost.backend.models.Users
import com.patidost.backend.plugins.AuthenticationException
import com.patidost.backend.plugins.PatiPuanLimitExceededException
import com.patidost.backend.plugins.dbQuery
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

interface SocialRepository {
    suspend fun givePatiPuan(giverId: String, receiverPetId: String)
}

class SocialRepositoryImpl : SocialRepository {

    private val DAILY_LIMIT: Long = 5

    override suspend fun givePatiPuan(giverId: String, receiverPetId: String) {
        val giver = dbQuery {
            Users.select { Users.id eq giverId }.singleOrNull()
        } ?: throw AuthenticationException()

        // Check if the user is NOT GOLD and has reached the daily limit
        if (giver[Users.subscriptionLevel] != SubscriptionLevel.GOLD) {
            val twentyFourHoursAgo = Instant.now().minus(24, ChronoUnit.HOURS)

            val transactionsInLast24Hours = dbQuery {
                PatiPuanTransactions.select {
                    (PatiPuanTransactions.giverId eq giverId) and (PatiPuanTransactions.transactionTime greaterEq twentyFourHoursAgo)
                }.count()
            }

            if (transactionsInLast24Hours >= DAILY_LIMIT) {
                throw PatiPuanLimitExceededException()
            }
        }

        // If all checks pass, create the transaction
        dbQuery {
            PatiPuanTransactions.insert {
                it[id] = UUID.randomUUID().toString()
                it[this.giverId] = giverId
                it[this.receiverPetId] = receiverPetId
            }
        }
    }
}
