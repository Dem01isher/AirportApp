package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "insurance", primaryKeys = ["serviceName", "typeOf"])
data class InsuranceEntity(
    val typeOf: String,
    val serviceName: String,
    val price: Int,
    val term: String,
    val formOfInsurance: String
)
