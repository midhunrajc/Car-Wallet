package data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import domain.model.Vehicle

@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val brand: String,
    val model: String,
    val fuelType: String,
    val vehicleNumber: String,
    val yearOfPurchase: Int,
    val ownerName: String
)

fun VehicleEntity.toDomain(): Vehicle {
    return Vehicle(
        id = id,
        brand = brand,
        model = model,
        fuelType = fuelType,
        vehicleNumber = vehicleNumber,
        yearOfPurchase = yearOfPurchase,
        ownerName = ownerName
    )
}

fun Vehicle.toEntity(): VehicleEntity {
    return VehicleEntity(
        id = id,
        brand = brand,
        model = model,
        fuelType = fuelType,
        vehicleNumber = vehicleNumber,
        yearOfPurchase = yearOfPurchase,
        ownerName = ownerName
    )
}