package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headquarter")
data class HeadQuarterEntity(
    @PrimaryKey(autoGenerate = false)
    val numberOf:Int,
    val availabilityOfKitchen: String,
    val countOfLevels: Int,
    val numberOfBeds: Int,
    val entertainmentRoom: String
)