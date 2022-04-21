package com.leskov.airport.di

import android.content.Context
import androidx.room.Room
import com.leskov.airport.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AirportDatabase =
        Room.databaseBuilder(context, AirportDatabase::class.java, AirportDatabase.AIRPORT_DATABASE)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideAirportDao(database: AirportDatabase): AirportDao = database.airportDao

    @Provides
    @Singleton
    fun provideAirplaneDao(database: AirportDatabase) : AirplaneDao = database.airplaneDao


    @Provides
    @Singleton
    fun provideTeamDao(database: AirportDatabase) : TeamDao = database.teamDao

    @Provides
    @Singleton
    fun provideHeadQuarterDao(database: AirportDatabase) : HeadQuarterDao = database.headQuarterDao

    @Provides
    @Singleton
    fun provideRouteDao(database: AirportDatabase) : RouteDao = database.routeDao

    @Provides
    @Singleton
    fun provideInsuranceDao(database: AirportDatabase) : InsuranceDao = database.insuranceDao

    @Provides
    @Singleton
    fun provideRaceDao(database: AirportDatabase) : RaceDao = database.raceDao

    @Provides
    @Singleton
    fun provideAirCompanyDao(database: AirportDatabase) : AirCompanyDao = database.airCompanyDao

}