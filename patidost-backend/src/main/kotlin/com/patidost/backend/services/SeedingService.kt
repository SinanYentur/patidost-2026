package com.patidost.backend.services

import at.favre.lib.crypto.bcrypt.BCrypt
import com.patidost.backend.models.Pets
import com.patidost.backend.models.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * 🛡️ GÖREV-018: Veritabanı Tohumlama Servisi (Güvenlik Geliştirildi)
 * V2 (EVRİMSEL SIÇRAMA): Adapted to seed Users and Pets, removing obsolete Post data.
 */
object SeedingService {

    private data class UserData(val id: String, val username: String, val name: String, val passwordHash: String, val profileImageUrl: String)
    private data class PetData(val id: String, val ownerId: String, val name: String, val age: Int, val imageUrl: String)

    fun seed() {
        transaction {
            SchemaUtils.create(Users, Pets)

            if (Users.selectAll().count() == 0L) {
                val passwordHash = BCrypt.withDefaults().hashToString(12, "password".toCharArray())
                val usersToSeed = listOf(
                    UserData("1", "sibel", "SibelR.", passwordHash, "/images/sibel.jpg"),
                    UserData("2", "melis", "Melis", passwordHash, "/images/melis.jpg"),
                    UserData("3", "arda", "Arda", passwordHash, "/images/arda.jpg"),
                    UserData("4", "ceyda", "Ceyda", passwordHash, "/images/ceyda.jpg"),
                    UserData("5", "erkan", "Erkan", passwordHash, "/images/erkan.jpg")
                )

                Users.batchInsert(usersToSeed) { user ->
                    this[Users.id] = user.id
                    this[Users.username] = user.username
                    this[Users.name] = user.name
                    this[Users.passwordHash] = user.passwordHash
                    this[Users.profileImageUrl] = user.profileImageUrl
                }
            }

            if (Pets.selectAll().count() == 0L) {
                val petsToSeed = listOf(
                    PetData("pet1", "1", "Süslü", 2, "/images/suslu.jpg"),
                    PetData("pet2", "2", "Paşa", 3, "/images/pasa.jpg"),
                    PetData("pet3", "3", "Boncuk", 1, "/images/boncuk.jpg"),
                    PetData("pet4", "4", "Duman", 5, "/images/duman.jpg"),
                    PetData("pet5", "5", "Fındık", 4, "/images/findik.jpg")
                )

                Pets.batchInsert(petsToSeed) { pet ->
                    this[Pets.id] = pet.id
                    this[Pets.ownerId] = pet.ownerId
                    this[Pets.name] = pet.name
                    this[Pets.age] = pet.age
                    this[Pets.imageUrl] = pet.imageUrl
                }
            }
        }
    }
}
