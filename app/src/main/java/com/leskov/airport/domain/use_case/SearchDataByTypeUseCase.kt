package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.*
import com.leskov.airport.domain.entity.TypeOfEntity
import java.util.ArrayList
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

    private var type: String = ""

    fun setType(type: String){
        this.type = type
    }

    suspend fun searchSelectedTypeData(searchQuery: String) : List<Any?>{
        return when (type) {

            TypeOfEntity.AIRCOMPANY -> airCompanyRepository.searchData(searchQuery)

            TypeOfEntity.AIRPORT -> airportRepository.searchData(searchQuery)

            TypeOfEntity.AIRPLANE -> airplaneRepository.searchData(searchQuery)

            TypeOfEntity.RACE -> raceRepository.searchData(searchQuery)

            TypeOfEntity.HEADQUARTERS -> headQuarterRepository.searchData(searchQuery)

            TypeOfEntity.INSURANCE -> insuranceRepository.searchData(searchQuery)

            TypeOfEntity.ROUTE -> routeRepository.searchData(searchQuery)

            TypeOfEntity.TEAM -> teamRepository.searchData(searchQuery)

            TypeOfEntity.FETCH_ALL_DATA -> fetchAllData()

            else -> fetchAllData()
        }
    }

    private fun fetchAllData() : ArrayList<Any?> {
        val currentList : ArrayList<Any?> = arrayListOf()
        currentList.addAll(airCompanyRepository.fetchAllData())
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