package com.leskov.airport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.leskov.airport.data.local.converter.DataConverter
import com.leskov.airport.domain.entity.*

@TypeConverters(value = [DataConverter::class])
@Database(
    entities = [AirportEntity::class,
        AirplaneEntity::class,
        RaceEntity::class,
        HeadQuarterEntity::class,
        InsuranceEntity::class,
        RouteEntity::class,
        TeamEntity::class,
        AirCompanyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AirportDatabase : RoomDatabase() {

    abstract val airportDao: AirportDao

    abstract val airplaneDao: AirplaneDao

    abstract val raceDao: RaceDao

    abstract val headQuarterDao: HeadQuarterDao

    abstract val insuranceDao: InsuranceDao

    abstract val routeDao: RouteDao

    abstract val teamDao: TeamDao

    abstract val airCompanyDao: AirCompanyDao
}