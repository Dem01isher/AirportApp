package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.TypeOfEntity
import javax.inject.Inject

class SearchDataByTypeUseCase @Inject constructor(
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

    suspend fun searchSelectedTypeData(searchQuery: String): List<Any?> {
        return when (type) {

            TypeOfEntity.AIRCOMPANY -> airCompanyRepository.searchData(searchQuery)

            TypeOfEntity.AIRPORT -> airportRepository.searchData(searchQuery)

            TypeOfEntity.AIRPLANE -> airplaneRepository.searchData(searchQuery)

            TypeOfEntity.RACE -> raceRepository.searchData(searchQuery)

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.searchData(searchQuery)

            TypeOfEntity.INSURANCE -> insuranceRepository.searchData(searchQuery)

            TypeOfEntity.ROUTE -> routeRepository.searchData(searchQuery)

            TypeOfEntity.TEAM -> teamRepository.searchData(searchQuery)

            else -> emptyList()
        }
    }
}