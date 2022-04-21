package com.leskov.airport.data.repository

import com.leskov.airport.data.local.InsuranceDao
import com.leskov.airport.domain.entity.InsuranceEntity
import javax.inject.Inject

interface InsuranceRepository {
    fun fetchAllData() : List<InsuranceEntity?>

    suspend fun searchData(searchText: String) : List<InsuranceEntity?>

    suspend fun insertItem(item: InsuranceEntity)

    suspend fun updateItem(item: InsuranceEntity)

    suspend fun deleteItem(item: InsuranceEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: InsuranceDao) : InsuranceRepository{

        override fun fetchAllData(): List<InsuranceEntity?> = dao.fetchAllData()

        override suspend fun searchData(searchText: String): List<InsuranceEntity?> =
            dao.searchData(searchText)

        override suspend fun insertItem(item: InsuranceEntity) = dao.insertItem(item)

        override suspend fun updateItem(item: InsuranceEntity) = dao.updateItem(item)

        override suspend fun deleteItem(item: InsuranceEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()

    }
}