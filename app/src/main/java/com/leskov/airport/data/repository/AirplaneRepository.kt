package com.leskov.airport.data.repository

import com.leskov.airport.data.local.AirplaneDao
import com.leskov.airport.domain.entity.AirplaneEntity
import java.util.ArrayList
import javax.inject.Inject

interface AirplaneRepository {

    fun fetchAllData() : List<AirplaneEntity?>

    suspend fun searchData(searchText: String) : List<AirplaneEntity?>

    suspend fun insertItem(item: AirplaneEntity)

    suspend fun deleteItem(item: AirplaneEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: AirplaneDao) : AirplaneRepository {

        override fun fetchAllData(): List<AirplaneEntity?> = dao.fetchAllData()

        override suspend fun searchData(searchText: String): List<AirplaneEntity?> = dao.searchData(searchText)

        override suspend fun insertItem(item: AirplaneEntity) = dao.insertItem(item)

        override suspend fun deleteItem(item: AirplaneEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()
    }
}