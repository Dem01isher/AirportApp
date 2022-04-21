package com.leskov.airport.data.repository

import com.leskov.airport.data.local.RouteDao
import com.leskov.airport.domain.entity.RouteEntity
import javax.inject.Inject

interface RouteRepository {

    fun fetchAllData() : List<RouteEntity?>

    suspend fun searchData(searchText: String) : List<RouteEntity?>

    suspend fun insertItem(item: RouteEntity)

    suspend fun deleteItem(item: RouteEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: RouteDao) : RouteRepository{

        override fun fetchAllData(): List<RouteEntity?> = dao.fetchAllData()

        override suspend fun searchData(searchText: String): List<RouteEntity?> =
            dao.searchData(searchText)

        override suspend fun insertItem(item: RouteEntity) = dao.insertItem(item)

        override suspend fun deleteItem(item: RouteEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()

    }
}