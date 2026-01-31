package di

import data.local.dao.VehicleDao
import javax.inject.Inject

interface VehicleRepository
class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDao: VehicleDao
) : VehicleRepository
