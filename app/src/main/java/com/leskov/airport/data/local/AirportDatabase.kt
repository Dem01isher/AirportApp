package com.leskov.airport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leskov.airport.domain.entity.*

@Database(
    entities = [AirportEntity::class,
        AirplaneEntity::class,
        RaceEntity::class,
        HeadQuarterEntity::class,
        InsuranceEntity::class,
        RouteEntity::class,
        TeamEntity::class],
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

    abstract val headQuarterDao: HeadQuarterDao

    abstract val insuranceDao: InsuranceDao

    abstract val routeDao: RouteDao

    abstract val teamDao: TeamDao

}