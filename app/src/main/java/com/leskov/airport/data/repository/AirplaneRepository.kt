package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.AirplaneDao
import com.leskov.airport.domain.entity.AirplaneEntity
import javax.inject.Inject

interface AirplaneRepository {

    fun fetchAllData() : LiveData<List<AirplaneEntity?>>

    fun findItemByKey(producer: String) : AirplaneEntity

    fun searchData(searchText: String) : LiveData<List<AirplaneEntity?>>

    suspend fun insertItem(item: AirplaneEntity)

    suspend fun updateItem(item: AirplaneEntity)

    suspend fun deleteItem(item: AirplaneEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: AirplaneDao) : AirplaneRepository {

        override fun fetchAllData(): LiveData<List<AirplaneEntity?>> = dao.fetchAllData()

        override fun findItemByKey(producer: String): AirplaneEntity = dao.findItemByKey(producer)

        override fun searchData(searchText: String): LiveData<List<AirplaneEntity?>> = dao.searchData(searchText)

        override suspend fun insertItem(item: AirplaneEntity) = dao.insertItem(item)

        override suspend fun updateItem(item: AirplaneEntity) = dao.updateItem(item)

        override suspend fun deleteItem(item: AirplaneEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()
    }
}