package ui.home

import domain.model.Vehicle

data class HomeUiState(
    val isLoading: Boolean = false,
    val vehicles: List<Vehicle> = emptyList(),
    val selectedBrand: String? = null,
    val selectedFuelType: String? = null,
    val error: String? = null
)
