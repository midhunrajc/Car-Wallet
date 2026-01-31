package ui.addVehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import domain.usecase.AddVehicleUseCase

class AddVehicleViewModelFactory(
    private val addVehicleUseCase: AddVehicleUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddVehicleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddVehicleViewModel(addVehicleUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
