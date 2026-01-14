package com.patidost.backend.services

import com.patidost.backend.models.*
import com.patidost.backend.plugins.PatiPuanLimitExceededException
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.test.assertFailsWith

class SocialRepositoryTest {

    private lateinit var database: Database
    private val socialRepository: SocialRepository = SocialRepositoryImpl()

    @Before
    fun setup() {
        // Use an in-memory H2 database for testing
        database = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction(database) {
            // Create tables before each test
            SchemaUtils.create(Users, Pets, PatiPuanTransactions)
        }
    }

    @After
    fun teardown() {
        // Drop tables after each test to ensure isolation
        transaction(database) {
            SchemaUtils.drop(PatiPuanTransactions, Pets, Users)
        }
    }

    private suspend fun setupUserAndPet(subscriptionLevel: SubscriptionLevel): Pair<String, String> {
        return newSuspendedTransaction(db = database) {
            val userId = Users.insertUserForTest(subscriptionLevel)
            val petId = Pets.insertPetForTest(userId)
            Pair(userId, petId)
        }
    }

    @Test
    fun `Senaryo 1 (Limit Altı - Başarı) - FREE user can give Pati Puan under limit`() = runBlocking {
        val (freeUserId, petId) = setupUserAndPet(SubscriptionLevel.FREE)

        // Action: Give 3 Pati Puans (which is under the limit of 5)
        repeat(3) {
            socialRepository.givePatiPuan(freeUserId, petId)
        }
        // Assertion: No exception should be thrown. If we reach here, the test PASSED.
    }

    @Test
    fun `Senaryo 2 (Limit Aşımı - Red) - FREE user is blocked after 5 Pati Puans`() = runBlocking {
        val (freeUserId, petId) = setupUserAndPet(SubscriptionLevel.FREE)

        // Action: Give 5 Pati Puans successfully
        repeat(5) {
            socialRepository.givePatiPuan(freeUserId, petId)
        }

        // Assertion: The 6th attempt must fail with PatiPuanLimitExceededException
        assertFailsWith<PatiPuanLimitExceededException> {
            socialRepository.givePatiPuan(freeUserId, petId)
        }
    }

    @Test
    fun `Senaryo 3 (V I P Geçişi - Gold) - GOLD user has unlimited Pati Puan access`() = runBlocking {
        val (goldUserId, petId) = setupUserAndPet(SubscriptionLevel.GOLD)

        // Action: Give 10 Pati Puans, which is over the free limit
        repeat(10) {
            socialRepository.givePatiPuan(goldUserId, petId)
        }

        // Assertion: No exception should be thrown for a GOLD user. If we reach here, the test PASSED.
    }
}

// Helper functions to reduce boilerplate in tests
private fun Users.insertUserForTest(subscriptionLevel: SubscriptionLevel): String {
    val id = UUID.randomUUID().toString()
    transaction {
        Users.insertUser(id, "testuser", "Test User", "hash", subscriptionLevel)
    }
    return id
}

private fun Pets.insertPetForTest(ownerId: String): String {
    val id = UUID.randomUUID().toString()
    transaction {
        Pets.insertPet(id, ownerId, "Test Pet", 2, "url")
    }
    return id
}
