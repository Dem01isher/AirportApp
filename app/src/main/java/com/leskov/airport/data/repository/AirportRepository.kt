package com.leskov.airport.data.repository

import com.leskov.airport.data.local.AirportDao
import com.leskov.airport.domain.AirportEntity

interface AirportRepository {

    fun fetchAllData() : List<AirportEntity>

    fun insertItem(item: AirportEntity)

    fun deleteItem(item: AirportEntity)

    fun removeAllItems()

    class Base(private val airportDao: AirportDao): AirportRepository{

        override fun fetchAllData(): List<AirportEntity> = airportDao.fetchAllData()

        override fun insertItem(item: AirportEntity) = airportDao.insertItem(item)

        override fun deleteItem(item: AirportEntity) = airportDao.deleteItem(item)

        override fun removeAllItems() = airportDao.removeAllItems()

    }
}