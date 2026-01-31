package ui.addVehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.model.Vehicle
import domain.usecase.AddVehicleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddVehicleViewModel @Inject constructor(
    private val addVehicleUseCase: AddVehicleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddVehicleUiState())
    val uiState: StateFlow<AddVehicleUiState> = _uiState.asStateFlow()

    fun updateBrand(value: String) {
        _uiState.update { it.copy(brand = value) }
    }

    fun updateModel(value: String) {
        _uiState.update { it.copy(model = value) }
    }

    fun updateFuelType(value: String) {
        _uiState.update { it.copy(fuelType = value) }
    }

    fun updateVehicleNumber(value: String) {
        _uiState.update { it.copy(vehicleNumber = value) }
    }

    fun updateYear(value: String) {
        _uiState.update { it.copy(yearOfPurchase = value) }
    }

    fun updateOwnerName(value: String) {
        _uiState.update { it.copy(ownerName = value) }
    }

    fun saveVehicle() {
        val state = _uiState.value

        if (
            state.brand.isBlank() ||
            state.model.isBlank() ||
            state.fuelType.isBlank() ||
            state.vehicleNumber.isBlank() ||
            state.yearOfPurchase.isBlank()
        ) {
            _uiState.update { it.copy(error = "Please fill all mandatory fields") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, error = null) }

            addVehicleUseCase(
                Vehicle(
                    brand = state.brand,
                    model = state.model,
                    fuelType = state.fuelType,
                    vehicleNumber = state.vehicleNumber,
                    yearOfPurchase = state.yearOfPurchase.toInt(),
                    ownerName = state.ownerName
                )
            )

            _uiState.update {
                it.copy(
                    isSaving = false,
                    isSaved = true
                )
            }
        }
    }
}
