package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.FilterVehiclesUseCase
import domain.usecase.GetVehiclesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getVehiclesUseCase: GetVehiclesUseCase,
    private val filterVehiclesUseCase: FilterVehiclesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadVehicles()
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            getVehiclesUseCase()
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .collect { vehicles ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            vehicles = vehicles
                        )
                    }
                }
        }
    }

    fun applyFilter(brand: String?, fuelType: String?) {
        viewModelScope.launch {
            filterVehiclesUseCase(brand, fuelType)
                .collect { vehicles ->
                    _uiState.update {
                        it.copy(
                            vehicles = vehicles,
                            selectedBrand = brand,
                            selectedFuelType = fuelType
                        )
                    }
                }
        }
    }
}
