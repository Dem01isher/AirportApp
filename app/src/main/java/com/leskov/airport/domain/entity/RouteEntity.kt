package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "route", primaryKeys = ["status", "departureCountry"])
data class RouteEntity(
    val numberOf: Int,
    val status: String,
    val departureCountry: String,
    val destinationCountry: String,
    val length: String
)
