package com.leskov.airport.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "airport", primaryKeys = ["title", "city"])
data class AirportEntity(
    @ColumnInfo(name = "title")
    val title: String,
    val countOfTerminals: Int,
    val countryLocation: String,
    @ColumnInfo(name = "city")
    val city: String,
    val numberOfLanes: Int
) : Serializable