package com.leskov.airport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leskov.airport.domain.entity.AirportEntity

@Database(entities = [AirportEntity::class], version = 1, exportSchema = false)
abstract class AirportDatabase : RoomDatabase() {

    companion object {
        const val AIRPORT_DATABASE = "airport_database"
    }

    abstract val airportDao: AirportDao
}