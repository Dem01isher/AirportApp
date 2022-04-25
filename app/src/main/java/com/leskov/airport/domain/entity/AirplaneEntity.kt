package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airplane", primaryKeys = ["type", "model"])
data class AirplaneEntity(
    val number: Int,
    val type: String,
    val model: String,
    val loadCapacity: String,
    val producer: String
)