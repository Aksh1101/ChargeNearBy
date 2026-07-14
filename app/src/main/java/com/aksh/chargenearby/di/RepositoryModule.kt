package com.aksh.chargenearby.di


import com.aksh.chargenearby.data.FakeChargingStationRepository
import com.aksh.chargenearby.data.repository.ChargingStationRepositoryImpl
import com.aksh.chargenearby.domain.repository.ChargingStationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChargingStationRepository(
        implementation: ChargingStationRepositoryImpl
    ): ChargingStationRepository
}