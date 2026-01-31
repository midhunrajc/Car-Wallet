package di

import android.content.Context
import androidx.room.Room
import data.local.database.AppDatabase
import data.local.dao.VehicleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vehicle_db"
        ).build()
    }

    @Provides
    fun provideVehicleDao(
        database: AppDatabase
    ): VehicleDao {
        return database.vehicleDao()
    }
}
