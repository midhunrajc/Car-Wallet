package data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.local.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicles")
    fun getAllVehicles(): Flow<List<VehicleEntity>>

    @Query("""
        SELECT * FROM vehicles 
        WHERE (:brand IS NULL OR brand = :brand)
        AND (:fuelType IS NULL OR fuelType = :fuelType)
    """)
    fun filterVehicles(
        brand: String?,
        fuelType: String?
    ): Flow<List<VehicleEntity>>
}