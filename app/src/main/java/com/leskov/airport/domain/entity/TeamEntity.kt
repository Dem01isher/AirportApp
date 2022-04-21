package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey(autoGenerate = false)
    val numberOf: Int,
    val countOfPeople: Int,
    val countOfPilots: Int,
    val countOfEngineers: Int,
    val countFlightAttendants: Int,
    val numberOfMovers: Int
)