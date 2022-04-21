package com.leskov.airport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leskov.airport.domain.entity.AirplaneEntity
import com.leskov.airport.domain.entity.AirportEntity
import com.leskov.airport.domain.entity.RaceEntity

@Database(
    entities = [AirportEntity::class,
        AirplaneEntity::class,
        RaceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AirportDatabase : RoomDatabase() {

    companion object {
        const val AIRPORT_DATABASE = "airport_database"
    }

    abstract val airportDao: AirportDao

    abstract val airplaneDao: AirplaneDao

    abstract val raceDao: RaceDao

//    abstract val aircompanyDao: AircompanyDao
}