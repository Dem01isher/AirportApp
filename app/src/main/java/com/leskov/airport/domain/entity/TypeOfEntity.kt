package com.leskov.airport.domain.entity

object TypeOfEntity {
    const val AIRPORT = "Airport"
    const val AIRCOMPANY = "AirCompany"
    const val AIRPLANE = "Airplane"
    const val RACE = "Race"
    const val FETCH_ALL_DATA = "fetch all data"
}

val listOfTypes = arrayOf(
    TypeOfEntity.RACE,
    TypeOfEntity.AIRPORT,
    TypeOfEntity.AIRPLANE,
    TypeOfEntity.FETCH_ALL_DATA
)