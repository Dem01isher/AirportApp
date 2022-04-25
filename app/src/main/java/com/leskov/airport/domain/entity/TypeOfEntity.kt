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
}

val listOfTypes = arrayOf(
    TypeOfEntity.AIRPORT,
    TypeOfEntity.AIRCOMPANY,
    TypeOfEntity.AIRPLANE,
    TypeOfEntity.RACE,
    TypeOfEntity.ROUTE,
    TypeOfEntity.TEAM,
    TypeOfEntity.HEADQUARTERS,
    TypeOfEntity.INSURANCE
)

val listOfItems: List<MainMenuModel> = listOf(
    MainMenuModel(TypeOfEntity.AIRPLANE, R.string.airplane, 0),
    MainMenuModel(TypeOfEntity.AIRCOMPANY, R.string.aircompany, 0),
    MainMenuModel(TypeOfEntity.RACE, R.string.race, 0),
    MainMenuModel(TypeOfEntity.AIRPORT, R.string.airport, 0),
    MainMenuModel(TypeOfEntity.ROUTE, R.string.route,  0),
    MainMenuModel(TypeOfEntity.TEAM, R.string.team, 0),
    MainMenuModel(TypeOfEntity.HEADQUARTERS, R.string.headquarters, 0),
    MainMenuModel(TypeOfEntity.INSURANCE, R.string.insurance, 0)
)