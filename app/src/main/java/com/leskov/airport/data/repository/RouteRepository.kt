package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.RouteDao
import com.leskov.airport.domain.entity.RouteEntity
import javax.inject.Inject

interface RouteRepository {

    fun fetchAllData() : LiveData<List<RouteEntity?>>

    fun findItemByKey(departureCountry: String) : RouteEntity

    fun searchData(searchText: String) : LiveData<List<RouteEntity?>>

    suspend fun insertItem(item: RouteEntity)

    suspend fun updateItem(item: RouteEntity)

    suspend fun deleteItem(item: RouteEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: RouteDao) : RouteRepository{

        override fun fetchAllData(): LiveData<List<RouteEntity?>> = dao.fetchAllData()

        override fun findItemByKey(departureCountry: String): RouteEntity = dao.findItemByKey(departureCountry)

        override fun searchData(searchText: String): LiveData<List<RouteEntity?>> =
            dao.searchData(searchText)

        override suspend fun insertItem(item: RouteEntity) = dao.insertItem(item)

        override suspend fun updateItem(item: RouteEntity) = dao.updateItem(item)

        override suspend fun deleteItem(item: RouteEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()

    }
}