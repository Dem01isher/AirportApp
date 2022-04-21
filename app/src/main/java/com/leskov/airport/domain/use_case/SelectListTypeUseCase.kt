package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.AirplaneRepository
import com.leskov.airport.data.repository.AirportRepository
import com.leskov.airport.data.repository.RaceRepository
import com.leskov.airport.domain.entity.TypeOfEntity
import java.util.ArrayList
import javax.inject.Inject

class SelectListTypeUseCase @Inject constructor(
    private val airportRepository: AirportRepository,
    private val airplaneRepository: AirplaneRepository,
    private val raceRepository: RaceRepository
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

            TypeOfEntity.FETCH_ALL_DATA -> fetchAllData()

            else -> fetchAllData()
        }
    }

    private fun fetchAllData() : ArrayList<Any?>{
        val currentList : ArrayList<Any?> = arrayListOf()
        currentList.addAll(airplaneRepository.fetchAllData())
        currentList.addAll(airportRepository.fetchAllData())
        currentList.addAll(raceRepository.fetchAllData())
        return currentList
    }
}