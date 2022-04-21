package com.leskov.airport.di

import com.leskov.airport.base.dispatcher.DispatcherController
import com.leskov.airport.data.local.AirplaneDao
import com.leskov.airport.data.local.AirportDao
import com.leskov.airport.data.local.RaceDao
import com.leskov.airport.data.repository.AirplaneRepository
import com.leskov.airport.data.repository.AirportRepository
import com.leskov.airport.data.repository.RaceRepository
import com.leskov.airport.domain.use_case.InsertItemByTypeUseCase
import com.leskov.airport.domain.use_case.SelectListTypeUseCase
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
    fun provideAirportRepository(airportDao: AirportDao): AirportRepository =
        AirportRepository.Base(airportDao)

    @Provides
    @Singleton
    fun provideRaceRepository(raceDao: RaceDao): RaceRepository =
        RaceRepository.Base(raceDao)

    @Provides
    @Singleton
    fun provideAirplaneRepository(airplaneDao: AirplaneDao): AirplaneRepository =
        AirplaneRepository.Base(airplaneDao)

    @Provides
    @Singleton
    fun provideDispatcherController(): DispatcherController =
        DispatcherController.Base()

    @Provides
    @Singleton
    fun provideSelectListTypeUseCase(
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository
    ): SelectListTypeUseCase =
        SelectListTypeUseCase(airportRepository, airplaneRepository, raceRepository)

    @Provides
    @Singleton
    fun provideInsertItemByTypeUseCase(
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository
    ): InsertItemByTypeUseCase = InsertItemByTypeUseCase(airportRepository, airplaneRepository, raceRepository)

}