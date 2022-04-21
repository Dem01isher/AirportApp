package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.*
import javax.inject.Inject

class RemoveItemByTypeUseCase @Inject constructor(
    private val airCompanyRepository: AirCompanyRepository,
    private val airportRepository: AirportRepository,
    private val airplaneRepository: AirplaneRepository,
    private val raceRepository: RaceRepository,
    private val headQuarterRepository: HeadQuarterRepository,
    private val insuranceRepository: InsuranceRepository,
    private val routeRepository: RouteRepository,
    private val teamRepository: TeamRepository
){

    private var type: String = ""

    fun setType(type: String){
        this.type = type
    }

    suspend fun removeSelectedTypeItem(item: Any?){
        when (type){
            TypeOfEntity.AIRCOMPANY -> airCompanyRepository.deleteItem(item as AirCompanyEntity)

            TypeOfEntity.AIRPLANE -> airplaneRepository.deleteItem(item as AirplaneEntity)

            TypeOfEntity.AIRPORT -> airportRepository.deleteItem(item as AirportEntity)

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.deleteItem(item as HeadQuarterEntity)

            TypeOfEntity.INSURANCE -> insuranceRepository.deleteItem(item as InsuranceEntity)

            TypeOfEntity.RACE -> raceRepository.deleteItem(item as RaceEntity)

            TypeOfEntity.ROUTE -> routeRepository.deleteItem(item as RouteEntity)

            TypeOfEntity.TEAM -> teamRepository.deleteItem(item as TeamEntity)
        }
    }
}