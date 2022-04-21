package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.*
import javax.inject.Inject

class InsertItemByTypeUseCase @Inject constructor(
    private val airportRepository: AirportRepository,
    private val airplaneRepository: AirplaneRepository,
    private val raceRepository: RaceRepository,
    private val headQuarterRepository: HeadQuarterRepository,
    private val insuranceRepository: InsuranceRepository,
    private val routeRepository: RouteRepository,
    private val teamRepository: TeamRepository
) {
    private var type: String = ""

    fun setType(type: String){
        this.type = type
    }

    suspend fun insertItemByType(item: Any?){
        when (type) {

            TypeOfEntity.AIRPORT -> airportRepository.insertItem(item as AirportEntity)

            TypeOfEntity.AIRPLANE -> airplaneRepository.insertItem(item as AirplaneEntity)

            TypeOfEntity.RACE -> raceRepository.insertItem(item as RaceEntity)

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.insertItem(item as HeadQuarterEntity)

            TypeOfEntity.INSURANCE -> insuranceRepository.insertItem(item as InsuranceEntity)

            TypeOfEntity.ROUTE -> routeRepository.insertItem(item as RouteEntity)

            TypeOfEntity.TEAM -> teamRepository.insertItem(item as TeamEntity)

            else -> {
                // something
            }
        }
    }
}