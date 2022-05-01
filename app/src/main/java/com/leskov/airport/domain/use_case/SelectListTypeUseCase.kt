package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.TypeOfEntity
import javax.inject.Inject

class SelectListTypeUseCase @Inject constructor(
    private val airCompanyRepository: AirCompanyRepository,
    private val airportRepository: AirportRepository,
    private val airplaneRepository: AirplaneRepository,
    private val raceRepository: RaceRepository,
    private val headQuarterRepository: HeadQuarterRepository,
    private val insuranceRepository: InsuranceRepository,
    private val routeRepository: RouteRepository,
    private val teamRepository: TeamRepository
) {

    var type: String = ""

    fun selectType(type: String) {
        if (type.isNullOrBlank()) return
        this.type = type
    }

    fun fetchSelectedTypeList(): List<Any?> {
        return when (type) {

            TypeOfEntity.AIRCOMPANY -> airCompanyRepository.fetchAllData()

            TypeOfEntity.AIRPORT -> airportRepository.fetchAllData()

            TypeOfEntity.AIRPLANE -> airplaneRepository.fetchAllData()

            TypeOfEntity.RACE -> raceRepository.fetchAllData()

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.fetchAllData()

            TypeOfEntity.INSURANCE -> insuranceRepository.fetchAllData()

            TypeOfEntity.ROUTE -> routeRepository.fetchAllData()

            TypeOfEntity.TEAM -> teamRepository.fetchAllData()

            else -> emptyList()
        }
    }
}