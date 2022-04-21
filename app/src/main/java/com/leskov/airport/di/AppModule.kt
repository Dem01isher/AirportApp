package com.leskov.airport.di

import com.leskov.airport.base.dispatcher.DispatcherController
import com.leskov.airport.data.local.*
import com.leskov.airport.data.repository.*
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
    fun provideHeadQuarterRepository(headQuarterDao: HeadQuarterDao): HeadQuarterRepository =
        HeadQuarterRepository.Base(headQuarterDao)


    @Provides
    @Singleton
    fun provideInsuranceRepository(insuranceDao: InsuranceDao): InsuranceRepository =
        InsuranceRepository.Base(insuranceDao)

    @Provides
    @Singleton
    fun provideRouteRepository(routeDao: RouteDao): RouteRepository =
        RouteRepository.Base(routeDao)

    @Provides
    @Singleton
    fun provideTeamRepository(teamDao: TeamDao): TeamRepository =
        TeamRepository.Base(teamDao)

    @Provides
    @Singleton
    fun provideDispatcherController(): DispatcherController =
        DispatcherController.Base()

    @Provides
    @Singleton
    fun provideSelectListTypeUseCase(
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): SelectListTypeUseCase =
        SelectListTypeUseCase(
            airportRepository,
            airplaneRepository,
            raceRepository,
            headQuarterRepository,
            insuranceRepository,
            routeRepository,
            teamRepository
        )

    @Provides
    @Singleton
    fun provideInsertItemByTypeUseCase(
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): InsertItemByTypeUseCase = InsertItemByTypeUseCase(
        airportRepository,
        airplaneRepository,
        raceRepository,
        headQuarterRepository,
        insuranceRepository,
        routeRepository,
        teamRepository
    )

}