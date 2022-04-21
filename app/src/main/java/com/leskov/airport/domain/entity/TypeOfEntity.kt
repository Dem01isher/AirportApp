package com.leskov.airport.domain.entity

import com.leskov.airport.R
import com.leskov.airport.domain.model.MainMenuModel

object TypeOfEntity {
    const val AIRPORT = "Airport"
    const val AIRCOMPANY = "AirCompany"
    const val AIRPLANE = "Airplane"
    const val RACE = "Race"
    const val ROUTE = "Route"
    const val TEAM = "Team"
    const val HEADQUARTERS = "Headquarters"
    const val INSURANCE = "Insurance"
    const val FETCH_ALL_DATA = "fetch all data"
}

val listOfTypes = arrayOf(
    TypeOfEntity.AIRPORT,
    TypeOfEntity.AIRCOMPANY,
    TypeOfEntity.AIRPLANE,
    TypeOfEntity.RACE,
    TypeOfEntity.ROUTE,
    TypeOfEntity.TEAM,
    TypeOfEntity.HEADQUARTERS,
    TypeOfEntity.INSURANCE,
    TypeOfEntity.FETCH_ALL_DATA
)

val listOfItems: List<MainMenuModel> = listOf(
    MainMenuModel(TypeOfEntity.AIRPLANE, 0),
    MainMenuModel(TypeOfEntity.AIRCOMPANY, 0),
    MainMenuModel(TypeOfEntity.RACE, 0),
    MainMenuModel(TypeOfEntity.AIRPORT, 0),
    MainMenuModel(TypeOfEntity.ROUTE, 0),
    MainMenuModel(TypeOfEntity.TEAM, 0),
    MainMenuModel(TypeOfEntity.HEADQUARTERS, 0),
    MainMenuModel(TypeOfEntity.INSURANCE, 0)
)