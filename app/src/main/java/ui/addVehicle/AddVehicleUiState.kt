package ui.addVehicle

data class AddVehicleUiState(
    val brand: String = "",
    val model: String = "",
    val fuelType: String = "",
    val vehicleNumber: String = "",
    val yearOfPurchase: String = "",
    val ownerName: String = "",
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)
