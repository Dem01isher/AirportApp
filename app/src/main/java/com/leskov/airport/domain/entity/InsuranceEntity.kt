package com.leskov.airport.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "insurance")
data class InsuranceEntity(
    @PrimaryKey(autoGenerate = false)
    val typeOf: String,
    val serviceName: String,
    val price: Int,
    val term: String,
    val formOfInsurance: String
)
