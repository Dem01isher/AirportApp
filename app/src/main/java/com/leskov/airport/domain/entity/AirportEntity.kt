package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "airport")
data class AirportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val type: String,
    val model: String,
    val loadCapacity: String
) : Serializable