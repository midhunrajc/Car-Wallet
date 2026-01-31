package data.repository

import data.local.dao.VehicleDao
import data.local.entity.toDomain
import data.local.entity.toEntity
import domain.model.Vehicle
import domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehicleRepositoryImpl(
    private val dao: VehicleDao
) : VehicleRepository {

    override fun getVehicles(): Flow<List<Vehicle>> =
        dao.getAllVehicles().map { list ->
            list.map { it.toDomain() }
        }

    override fun filterVehicles(
        brand: String?,
        fuelType: String?
    ): Flow<List<Vehicle>> =
        dao.filterVehicles(brand, fuelType).map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun addVehicle(vehicle: Vehicle) {
        dao.insert(vehicle.toEntity())
    }
}

