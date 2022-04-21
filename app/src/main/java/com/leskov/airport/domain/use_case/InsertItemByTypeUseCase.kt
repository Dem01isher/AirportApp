package com.leskov.airport.domain.use_case

import com.leskov.airport.data.repository.AirplaneRepository
import com.leskov.airport.data.repository.AirportRepository
import com.leskov.airport.data.repository.RaceRepository
import com.leskov.airport.domain.entity.AirplaneEntity
import com.leskov.airport.domain.entity.AirportEntity
import com.leskov.airport.domain.entity.RaceEntity
import com.leskov.airport.domain.entity.TypeOfEntity
import javax.inject.Inject

class InsertItemByTypeUseCase @Inject constructor(
    private val airportRepository: AirportRepository,
    private val airplaneRepository: AirplaneRepository,
    private val raceRepository: RaceRepository
) {
    private var type: String = ""

    fun setType(type: String){
        this.type = type
    }

    suspend fun insertItemByType(item: Any?){
        when (type) {
            TypeOfEntity.AIRPORT -> {
                airportRepository.insertItem(item as AirportEntity)
            }
            TypeOfEntity.AIRPLANE -> {
                airplaneRepository.insertItem(item as AirplaneEntity)
            }
            TypeOfEntity.RACE -> {
                raceRepository.insertItem(item as RaceEntity)
            }
            else -> {
                // something
            }
        }
    }
}