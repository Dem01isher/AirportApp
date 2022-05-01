package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.TypeOfEntity
import javax.inject.Inject

class FindItemByTypeUseCase @Inject constructor(
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

    fun selectType(type: String){
        if (type.isNullOrBlank()) return
        this.type = type
    }

    fun findItemByType(key: Any?) : Any? {
        return when (type) {
            TypeOfEntity.AIRCOMPANY -> airCompanyRepository.findItemByKey(key as String)

            TypeOfEntity.AIRPLANE -> airplaneRepository.findItemByKey(key as String)

            TypeOfEntity.AIRPORT -> airportRepository.findItemByKey(key as String)

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.findItemByType(key as Int)

            TypeOfEntity.INSURANCE -> insuranceRepository.findItemByKey(key as String)

            TypeOfEntity.RACE -> raceRepository.findItemByKey(key as String)

            TypeOfEntity.ROUTE -> routeRepository.findItemByKey(key as String)

            TypeOfEntity.TEAM -> teamRepository.findItemByKey(key as Int)

            else -> {
                // do something
            }
        }
    }
}