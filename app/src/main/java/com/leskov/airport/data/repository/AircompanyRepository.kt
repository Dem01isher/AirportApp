package com.leskov.airport.data.repository

import javax.inject.Inject

//interface AircompanyRepository {
//
//    fun fetchAllData() : List<AircompanyEntity>
//
//    suspend fun searchData(searchText: String) : List<AircompanyEntity>
//
//    suspend fun insertItem(item: AircompanyEntity)
//
//    suspend fun deleteItem(item: AircompanyEntity)
//
//    suspend fun removeAllItems()
//
//    class Base @Inject constructor(private val dao: AircompanyDao) : AircompanyRepository {
//
//        override fun fetchAllData(): List<AircompanyEntity> = dao.fetchAllData()
//
//        override suspend fun searchData(searchText: String): List<AircompanyEntity> = dao.searchData(searchText)
//
//        override suspend fun insertItem(item: AircompanyEntity) = dao.insertItem(item)
//
//        override suspend fun deleteItem(item: AircompanyEntity) = dao.deleteItem(item)
//
//        override suspend fun removeAllItems() = dao.removeAllItems()
//    }
//}