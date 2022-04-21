package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airplane")
data class AirplaneEntity(
    @PrimaryKey(autoGenerate = false)
    val number: Int,
    val type: String,
    val model: String,
    val loadCapacity: String,
    val producer: String
)