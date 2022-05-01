package com.leskov.airport.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leskov.airport.domain.entity.AirportEntity

@ProvidedTypeConverter
class DataConverter {
    @TypeConverter
    fun fromCountryLangList(value: List<AirportEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<AirportEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<AirportEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<AirportEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}