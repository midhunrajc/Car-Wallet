package ui.addVehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.local.dao.VehicleDao
import data.local.entity.VehicleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddVehicleViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {

    private var brand: String = ""
    private var model: String = ""
    private var fuelType: String = ""
    private var vehicleNumber: String = ""
    private var ownerName: String = ""

    private val _allVehicles = MutableStateFlow<List<VehicleEntity>>(emptyList())
    val allVehicles: StateFlow<List<VehicleEntity>> = _allVehicles

    fun updateBrand(value: String) {
        brand = value
    }

    fun updateFuelType(value: String) {
        fuelType = value
    }


    fun updateModel(value: String) {
        model = value
    }

    fun updateVehicleNumber(value: String) {
        vehicleNumber = value
    }

    fun updateOwnerName(value: String) {
        ownerName = value
    }

    fun saveVehicle() {
        println("Saving: brand=$brand number=$vehicleNumber owner=$ownerName")
        val vehicle = VehicleEntity(
            brand = brand,
            model = model,
            fuelType = fuelType,
            vehicleNumber = vehicleNumber,
            yearOfPurchase = 2026, // temporary, you can use input
            ownerName = ownerName
        )

        viewModelScope.launch {
            vehicleDao.insert(vehicle)
            refreshVehicles()
        }
    }

    fun refreshVehicles() {
        viewModelScope.launch {
            vehicleDao.getAllVehicles().collect { list ->
                _allVehicles.value = list
            }
        }
    }
}
