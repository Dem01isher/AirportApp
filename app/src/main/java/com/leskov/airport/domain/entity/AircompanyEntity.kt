package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aircompany")
data class AircompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val int: Int,
    val nameOfCompany: String,
    val type: String,
    val number: String,
    val dateOfFoundation: String,
    val officeLocation: String
)