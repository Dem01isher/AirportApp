package com.leskov.airport.di

import com.leskov.airport.data.local.*
import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.use_case.*
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
    fun provideAirCompanyRepository(airCompanyDao: AirCompanyDao): AirCompanyRepository =
        AirCompanyRepository.Base(airCompanyDao)

    @Provides
    @Singleton
    fun provideSelectListTypeUseCase(
        airCompanyRepository: AirCompanyRepository,
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): SelectListTypeUseCase =
        SelectListTypeUseCase(
            airCompanyRepository,
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
        airCompanyRepository: AirCompanyRepository,
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): InsertItemByTypeUseCase = InsertItemByTypeUseCase(
        airCompanyRepository,
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
    fun provideUpdateItemByTypeUseCase(
        airCompanyRepository: AirCompanyRepository,
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): UpdateItemByTypeUseCase =
        UpdateItemByTypeUseCase(
            airCompanyRepository,
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
    fun provideSearchDataByTypeUseCase(
        airCompanyRepository: AirCompanyRepository,
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): SearchDataByTypeUseCase =
        SearchDataByTypeUseCase(
            airCompanyRepository,
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
    fun provideRemoveItemByTypeUseCase(
        airCompanyRepository: AirCompanyRepository,
        airportRepository: AirportRepository,
        raceRepository: RaceRepository,
        airplaneRepository: AirplaneRepository,
        headQuarterRepository: HeadQuarterRepository,
        insuranceRepository: InsuranceRepository,
        routeRepository: RouteRepository,
        teamRepository: TeamRepository
    ): RemoveItemByTypeUseCase =
        RemoveItemByTypeUseCase(
            airCompanyRepository,
            airportRepository,
            airplaneRepository,
            raceRepository,
            headQuarterRepository,
            insuranceRepository,
            routeRepository,
            teamRepository
        )

}