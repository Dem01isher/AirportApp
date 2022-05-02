package com.leskov.airport.data.repository

import androidx.lifecycle.LiveData
import com.leskov.airport.data.local.TeamDao
import com.leskov.airport.domain.entity.TeamEntity
import javax.inject.Inject

interface TeamRepository {

    fun fetchAllData() : LiveData<List<TeamEntity?>>

    fun findItemByKey(countOfPeople: Int) : TeamEntity

    fun searchData(searchText: String) : LiveData<List<TeamEntity?>>

    suspend fun insertItem(item: TeamEntity)

    suspend fun updateItem(item: TeamEntity)

    suspend fun deleteItem(item: TeamEntity)

    suspend fun removeAllItems()

    class Base @Inject constructor(private val dao: TeamDao) : TeamRepository{

        override fun fetchAllData(): LiveData<List<TeamEntity?>> = dao.fetchAllData()

        override fun findItemByKey(countOfPeople: Int): TeamEntity = dao.findItemByKey(countOfPeople)

        override fun searchData(searchText: String): LiveData<List<TeamEntity?>> =
            dao.searchData(searchText)

        override suspend fun insertItem(item: TeamEntity) = dao.insertItem(item)

        override suspend fun updateItem(item: TeamEntity) = dao.updateItem(item)

        override suspend fun deleteItem(item: TeamEntity) = dao.deleteItem(item)

        override suspend fun removeAllItems() = dao.removeAllItems()

    }
}