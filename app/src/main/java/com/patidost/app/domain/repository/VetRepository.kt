package com.patidost.app.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Vet Repository - V10000.26000 Beyond HubX Edition.
 * Rule 125: Automated emergency redirect to verified experts.
 * RVWL: Integrated Tele-Health and Veterinary Clinic Bridge.
 */
interface VetRepository {
    
    /**
     * Real-time availability of verified veterinarians.
     */
    fun getOnlineVets(): Flow<List<VetExpert>>

    /**
     * Trigger secure RTC session for emergency consultation.
     */
    suspend fun startConsultation(vetId: String): Result<String>

    /**
     * Sync with local veterinary databases (Geo-location).
     */
    suspend fun findNearestClinics(lat: Double, lng: Double): List<VetClinic>
}

data class VetExpert(val id: String, val name: String, val licenseId: String, val isEmergencyReady: Boolean)
data class VetClinic(val name: String, val address: String, val phone: String, val distanceKm: Double)
