package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "route")
data class RouteEntity(
    @PrimaryKey(autoGenerate = false)
    val numberOf: Int,
    val status: String,
    val departureCountry: String,
    val destinationCountry: String,
    val length: String
)
