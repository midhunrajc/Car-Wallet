package domain.usecase

import domain.model.Vehicle
import domain.repository.VehicleRepository

class AddVehicleUseCase(private val repository: VehicleRepository) {
    suspend operator fun invoke(vehicle: Vehicle) {
        repository.addVehicle(vehicle)
    }
}
