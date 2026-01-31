package ui.addVehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.local.dao.VehicleDao

class AddVehicleViewModelFactory(
    private val vehicleDao: VehicleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddVehicleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddVehicleViewModel(vehicleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
