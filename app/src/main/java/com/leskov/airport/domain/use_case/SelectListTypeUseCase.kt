package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.TypeOfEntity
import java.util.ArrayList
import javax.inject.Inject

class SelectListTypeUseCase @Inject constructor(
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

    fun fetchSelectedTypeList(): List<Any?> {
        return when (type) {

            TypeOfEntity.AIRPORT -> airportRepository.fetchAllData()

            TypeOfEntity.AIRPLANE -> airplaneRepository.fetchAllData()

            TypeOfEntity.RACE -> raceRepository.fetchAllData()

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.fetchAllData()

            TypeOfEntity.INSURANCE -> insuranceRepository.fetchAllData()

            TypeOfEntity.ROUTE -> routeRepository.fetchAllData()

            TypeOfEntity.TEAM -> teamRepository.fetchAllData()

            TypeOfEntity.FETCH_ALL_DATA -> fetchAllData()

            else -> fetchAllData()
        }
    }

    private fun fetchAllData() : ArrayList<Any?>{
        val currentList : ArrayList<Any?> = arrayListOf()
        currentList.addAll(airplaneRepository.fetchAllData())
        currentList.addAll(airportRepository.fetchAllData())
        currentList.addAll(raceRepository.fetchAllData())
        currentList.addAll(headQuarterRepository.fetchAllData())
        currentList.addAll(insuranceRepository.fetchAllData())
        currentList.addAll(routeRepository.fetchAllData())
        currentList.addAll(teamRepository.fetchAllData())
        return currentList
    }
}