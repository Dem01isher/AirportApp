package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.AirportDao
import com.leskov.airport.domain.entity.AirportEntity
import javax.inject.Inject

interface AirportRepository {

    fun fetchAllData() : LiveData<List<AirportEntity?>>

    fun findItemByKey(city: String) : AirportEntity

    fun searchData(searchText: String) : LiveData<List<AirportEntity?>>

    suspend fun insertItem(item: AirportEntity)

    suspend fun updateItem(item: AirportEntity)

    suspend fun deleteItem(item: AirportEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val airportDao: AirportDao): AirportRepository{

        override fun fetchAllData(): LiveData<List<AirportEntity?>> = airportDao.fetchAllData()

        override fun findItemByKey(city: String): AirportEntity = airportDao.findItemByKey(city)

        override fun searchData(searchText: String): LiveData<List<AirportEntity?>> = airportDao.searchData(searchText)

        override suspend fun insertItem(item: AirportEntity) = airportDao.insertItem(item)

        override suspend fun updateItem(item: AirportEntity) = airportDao.updateItem(item)

        override suspend fun deleteItem(item: AirportEntity) = airportDao.deleteItem(item)

        override suspend fun removeAllItems() = airportDao.removeAllItems()

    }
}