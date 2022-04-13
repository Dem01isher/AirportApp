package com.leskov.airport.di

import android.content.Context
import androidx.room.Room
import com.leskov.airport.data.local.AirportDao
import com.leskov.airport.data.local.AirportDatabase
import com.leskov.airport.data.repository.AirportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AirportDatabase =
        Room.databaseBuilder(context, AirportDatabase::class.java, AirportDatabase.AIRPORT_DATABASE)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDao(database: AirportDatabase): AirportDao = database.airportDao

    @Provides
    @Singleton
    fun provideAirportRepository(airportDao: AirportDao): AirportRepository =
        AirportRepository.Base(airportDao)
}