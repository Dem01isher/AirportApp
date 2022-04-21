package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "race")
data class RaceEntity(
    @PrimaryKey(autoGenerate = true)
    val numberOfRace: Int,
    val timeOfDeparture: String,
    val arrivalTime: String,
    val flightTime: String,
    val typeOfRace: String
)