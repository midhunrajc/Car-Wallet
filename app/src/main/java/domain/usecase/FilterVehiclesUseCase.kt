package domain.usecase

import domain.model.Vehicle
import domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow

class FilterVehiclesUseCase(
    private val repository: VehicleRepository
) {

    operator fun invoke(
        brand: String?,
        fuelType: String?
    ): Flow<List<Vehicle>> {
        return repository.filterVehicles(brand, fuelType)
    }
}
