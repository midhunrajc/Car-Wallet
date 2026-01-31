package domain.repository

import data.local.dao.VehicleDao
import data.local.entity.VehicleEntity
import domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    fun getVehicles(): Flow<List<Vehicle>>

    fun filterVehicles(
        brand: String?,
        fuelType: String?
    ): Flow<List<Vehicle>>

    suspend fun addVehicle(vehicle: Vehicle)
}
