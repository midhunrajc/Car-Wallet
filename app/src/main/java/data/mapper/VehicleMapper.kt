package data.mapper

import domain.model.Vehicle
import data.local.entity.VehicleEntity

object VehicleMapper {

    fun toDomain(entity: VehicleEntity): Vehicle {
        return Vehicle(
            id = entity.id,
            brand = entity.brand,
            model = entity.model,
            fuelType = entity.fuelType,
            vehicleNumber = entity.vehicleNumber,
            yearOfPurchase = entity.yearOfPurchase,
            ownerName = entity.ownerName
        )
    }

    fun toEntity(model: Vehicle): VehicleEntity {
        return VehicleEntity(
            id = model.id,
            brand = model.brand,
            model = model.model,
            fuelType = model.fuelType,
            vehicleNumber = model.vehicleNumber,
            yearOfPurchase = model.yearOfPurchase,
            ownerName = model.ownerName
        )
    }
}
