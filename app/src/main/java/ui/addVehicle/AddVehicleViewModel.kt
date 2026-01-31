package ui.addVehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.dao.VehicleDao
import data.local.entity.VehicleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddVehicleViewModel(private val vehicleDao: VehicleDao) : ViewModel() {

    private var brand: String = ""
    private var model: String = ""
    private var fuelType: String = ""
    private var vehicleNumber: String = ""
    private var ownerName: String = ""

    // Expose all vehicles as StateFlow
    private val _allVehicles = MutableStateFlow<List<VehicleEntity>>(emptyList())
    val allVehicles: StateFlow<List<VehicleEntity>> = _allVehicles

    init {
        // Load all vehicles from DB automatically
        viewModelScope.launch {
            vehicleDao.getAllVehicles().collect { list ->
                _allVehicles.value = list
            }
        }
    }

    // Update methods
    fun updateBrand(value: String) { brand = value }
    fun updateModel(value: String) { model = value }
    fun updateFuelType(value: String) { fuelType = value }
    fun updateVehicleNumber(value: String) { vehicleNumber = value }
    fun updateOwnerName(value: String) { ownerName = value }

    fun saveVehicle() {
        val vehicle = VehicleEntity(
            brand = brand,
            model = model,
            fuelType = fuelType,
            vehicleNumber = vehicleNumber,
            yearOfPurchase = 2026, // Or get from input
            ownerName = ownerName
        )

        viewModelScope.launch {
            vehicleDao.insert(vehicle)
            // No need to refresh manually, flow will emit new list automatically
        }
    }
}
