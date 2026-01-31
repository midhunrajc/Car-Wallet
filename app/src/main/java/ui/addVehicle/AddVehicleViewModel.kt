package ui.addVehicle

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddVehicleViewModel @Inject constructor() : ViewModel() {

    var brand: String? = null
        private set

    private var vehicleNumber: String? = null
    private var ownerName: String? = null
    private var model: String = ""

    fun updateBrand(value: String) {
        brand = value
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
    }
}
