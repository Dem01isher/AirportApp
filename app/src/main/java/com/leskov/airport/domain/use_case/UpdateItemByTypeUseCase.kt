package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.*
import javax.inject.Inject

class UpdateItemByTypeUseCase @Inject constructor(
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

    suspend fun updateSelectedTypeItem(item: Any?){
        when (type){
            TypeOfEntity.AIRCOMPANY -> airCompanyRepository.updateItem(item as AirCompanyEntity)

            TypeOfEntity.AIRPLANE -> airplaneRepository.updateItem(item as AirplaneEntity)

            TypeOfEntity.AIRPORT -> airportRepository.updateItem(item as AirportEntity)

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.updateItem(item as HeadQuarterEntity)

            TypeOfEntity.INSURANCE -> insuranceRepository.updateItem(item as InsuranceEntity)

            TypeOfEntity.RACE -> raceRepository.updateItem(item as RaceEntity)

            TypeOfEntity.ROUTE -> routeRepository.updateItem(item as RouteEntity)

            TypeOfEntity.TEAM -> teamRepository.updateItem(item as TeamEntity)
        }
    }
}