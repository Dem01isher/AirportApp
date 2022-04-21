package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "airCompany", primaryKeys = ["nameOf", "officeLocation"])
data class AirCompanyEntity(
    val nameOf: String,
    val officeLocation: String,
    val typeOf: String,
    val contactNumber: String,
    val dateOfFoundation: String
) : Serializable