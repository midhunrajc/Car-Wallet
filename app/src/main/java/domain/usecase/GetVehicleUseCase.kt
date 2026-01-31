package domain.usecase

import domain.model.Vehicle
import domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow

class GetVehiclesUseCase(
    private val repository: VehicleRepository
) {
    operator fun invoke(): Flow<List<Vehicle>> {
        return repository.getVehicles()
    }
}
