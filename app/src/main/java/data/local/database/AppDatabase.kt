package data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.dao.VehicleDao
import data.local.entity.VehicleEntity

@Database(entities = [VehicleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
