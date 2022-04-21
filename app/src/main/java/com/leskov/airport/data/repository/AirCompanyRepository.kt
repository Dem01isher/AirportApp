package com.leskov.airport.data.repository

import com.leskov.airport.data.local.AirCompanyDao
import com.leskov.airport.domain.entity.AirCompanyEntity
import com.leskov.airport.domain.entity.AirplaneEntity
import javax.inject.Inject

interface AirCompanyRepository {

    fun fetchAllData() : List<AirCompanyEntity?>

    suspend fun searchData(searchText: String) : List<AirCompanyEntity?>

    suspend fun insertItem(item: AirCompanyEntity)

    suspend fun deleteItem(item: AirCompanyEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val airCompanyDao: AirCompanyDao) : AirCompanyRepository {
        override fun fetchAllData(): List<AirCompanyEntity?> = airCompanyDao.fetchAllData()

        override suspend fun searchData(searchText: String): List<AirCompanyEntity?> = airCompanyDao.searchData(searchText)

        override suspend fun insertItem(item: AirCompanyEntity) = airCompanyDao.insertItem(item)

        override suspend fun deleteItem(item: AirCompanyEntity) = airCompanyDao.deleteItem(item)

        override suspend fun removeAllItems() = airCompanyDao.removeAllItems()

    }
}