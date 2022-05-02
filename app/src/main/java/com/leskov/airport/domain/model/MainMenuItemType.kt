package com.leskov.airport.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.leskov.airport.R
import com.leskov.airport.domain.entity.TypeOfEntity

enum class MainMenuItemType(val title: String, @StringRes val titleRes: Int){
    AIRPLANE(TypeOfEntity.AIRPLANE, R.string.airplane),
    AIRCOMPANY(TypeOfEntity.AIRCOMPANY, R.string.aircompany),
    RACE(TypeOfEntity.RACE, R.string.race),
    AIRPORT(TypeOfEntity.AIRPORT, R.string.airport),
    ROUTE(TypeOfEntity.ROUTE, R.string.route),
    TEAM(TypeOfEntity.TEAM, R.string.team),
    HEADQUARTERS(TypeOfEntity.HEADQUARTERS, R.string.headquarters),
    INSURANCE(TypeOfEntity.INSURANCE, R.string.insurance)
}