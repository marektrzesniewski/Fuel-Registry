package com.markstudio.fuelregistry.di

import android.app.Application
import androidx.room.Room
import com.markstudio.fuelregistry.feature_fuel_registry.data.data_source.RefuelDatabase
import com.markstudio.fuelregistry.feature_fuel_registry.data.repository.RefuelRepositoryImpl
import com.markstudio.fuelregistry.feature_fuel_registry.domain.repository.RefuelRepository
import com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case.AddRefuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case.DeleteRefuel
import com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case.GetRefuels
import com.markstudio.fuelregistry.feature_fuel_registry.domain.use_case.RefuelUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRefuelDatabase(app: Application): RefuelDatabase {
        return Room.databaseBuilder(
            app,
            RefuelDatabase::class.java,
            RefuelDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideRefuelRepository(db: RefuelDatabase): RefuelRepository {
        return RefuelRepositoryImpl(db.refuelDao)
    }

    @Provides
    @Singleton
    fun providesRefuelUseCases(repository: RefuelRepository) : RefuelUseCases {
        return RefuelUseCases(
            getRefuels = GetRefuels(repository),
            addRefuel = AddRefuel(repository),
            deleteRefuel = DeleteRefuel(repository)
        )
    }
}