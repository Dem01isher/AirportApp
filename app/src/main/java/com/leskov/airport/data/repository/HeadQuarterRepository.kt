package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.HeadQuarterDao
import com.leskov.airport.domain.entity.HeadQuarterEntity
import javax.inject.Inject

interface HeadQuarterRepository {

    fun fetchAllData() : List<HeadQuarterEntity?>

    fun findItemByType(numberOf: Int) : HeadQuarterEntity

    suspend fun searchData(searchText: String) : List<HeadQuarterEntity?>

    suspend fun insertItem(item: HeadQuarterEntity)

    suspend fun updateItem(item: HeadQuarterEntity)

    suspend fun deleteItem(item: HeadQuarterEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: HeadQuarterDao) : HeadQuarterRepository{

        override fun fetchAllData(): List<HeadQuarterEntity?> = dao.fetchAllData()

        override fun findItemByType(numberOf: Int): HeadQuarterEntity = dao.findItemByType(numberOf)

        override suspend fun searchData(searchText: String): List<HeadQuarterEntity?> =
            dao.searchData(searchText)

        override suspend fun insertItem(item: HeadQuarterEntity) = dao.insertItem(item)

        override suspend fun updateItem(item: HeadQuarterEntity) = dao.updateItem(item)

        override suspend fun deleteItem(item: HeadQuarterEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()

    }
}