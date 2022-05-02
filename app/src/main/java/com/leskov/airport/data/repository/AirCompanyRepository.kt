package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.AirCompanyDao
import com.leskov.airport.domain.entity.AirCompanyEntity
import javax.inject.Inject

interface AirCompanyRepository {

    fun fetchAllData() : LiveData<List<AirCompanyEntity?>>

    fun findItemByKey(name: String) : AirCompanyEntity

    fun searchData(searchText: String) : LiveData<List<AirCompanyEntity?>>

    suspend fun insertItem(item: AirCompanyEntity)

    suspend fun updateItem(item: AirCompanyEntity)

    suspend fun deleteItem(item: AirCompanyEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val airCompanyDao: AirCompanyDao) : AirCompanyRepository {
        override fun fetchAllData(): LiveData<List<AirCompanyEntity?>> = airCompanyDao.fetchAllData()

        override fun findItemByKey(name: String): AirCompanyEntity = airCompanyDao.findItemByKey(name)

        override fun searchData(searchText: String): LiveData<List<AirCompanyEntity?>> = airCompanyDao.searchData(searchText)

        override suspend fun insertItem(item: AirCompanyEntity) = airCompanyDao.insertItem(item)

        override suspend fun updateItem(item: AirCompanyEntity) = airCompanyDao.updateItem(item)

        override suspend fun deleteItem(item: AirCompanyEntity) = airCompanyDao.deleteItem(item)

        override suspend fun removeAllItems() = airCompanyDao.removeAllItems()

    }
}