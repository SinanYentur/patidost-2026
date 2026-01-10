package com.patidost.backend.models

import org.jetbrains.exposed.sql.Table

/**
 * 🛡️ GÖREV-013 & 018: Veritabanı DNA'sı - Tablo Tanımlamaları (Güvenlik Geliştirildi)
 * V3 (EVRİMSEL SIÇRAMA): Replaced `Posts` with `Pets` to align with core app purpose.
 */

object Users : Table() {
    val id = varchar("id", 128)
    val username = varchar("username", 100).uniqueIndex()
    val name = varchar("name", 128)
    val passwordHash = varchar("password_hash", 256)
    val profileImageUrl = varchar("profile_image_url", 256)
    val birthDate = varchar("birth_date", 32).nullable()
    val hasPet = bool("has_pet").nullable()
    // TODO: Add user's location for distance calculation.

    override val primaryKey = PrimaryKey(id)
}

object Pets : Table() {
    val id = varchar("id", 128)
    val ownerId = varchar("owner_id", 128) references Users.id
    val name = varchar("name", 128)
    val age = integer("age")
    val imageUrl = varchar("image_url", 256)
    // TODO: Add pet's breed, gender, location, etc.

    override val primaryKey = PrimaryKey(id)
}
