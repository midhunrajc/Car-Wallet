package domain.model

data class Vehicle(
    val id: Int = 0,
    val brand: String,
    val model: String,
    val fuelType: String,
    val vehicleNumber: String,
    val yearOfPurchase: Int,
    val ownerName: String
)