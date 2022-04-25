package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.RaceDao
import com.leskov.airport.domain.entity.RaceEntity
import javax.inject.Inject

interface RaceRepository {

    fun fetchAllData() : List<RaceEntity?>

    fun findItemByKey(typeOfRace: String) : RaceEntity

    suspend fun searchData(searchText: String) : List<RaceEntity?>

    suspend fun insertItem(item: RaceEntity)

    suspend fun updateItem(item: RaceEntity)

    suspend fun deleteItem(item: RaceEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: RaceDao) : RaceRepository{
        override fun fetchAllData(): List<RaceEntity?> = dao.fetchAllData()

        override fun findItemByKey(typeOfRace: String): RaceEntity = dao.findItemByKey(typeOfRace)

        override suspend fun searchData(searchText: String): List<RaceEntity?> =
            dao.searchData(searchText)

        override suspend fun insertItem(item: RaceEntity) = dao.insertItem(item)

        override suspend fun updateItem(item: RaceEntity) = dao.updateItem(item)

        override suspend fun deleteItem(item: RaceEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()

    }
}