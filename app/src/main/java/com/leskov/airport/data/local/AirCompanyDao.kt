package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.AirCompanyEntity
import java.util.ArrayList

@Dao
interface AirCompanyDao {

    @Query("SELECT * FROM airCompany")
    fun fetchAllData() : LiveData<List<AirCompanyEntity?>>

    @Query("SELECT * FROM airCompany WHERE nameOf LIKE:name")
    fun findItemByKey(name: String) : AirCompanyEntity

    @Query("SELECT * FROM airCompany WHERE officeLocation LIKE '%' || :searchText|| '%' OR countOfLanes LIKE '%' || :searchText|| '%' ")
    fun searchData(searchText: String) : LiveData<List<AirCompanyEntity?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AirCompanyEntity::class)
    suspend fun insertItem(item: AirCompanyEntity)

    @Delete(entity = AirCompanyEntity::class)
    suspend fun deleteItem(item: AirCompanyEntity)

    @Update
    suspend fun updateItem(item: AirCompanyEntity)

    @Query("DELETE FROM airCompany")
    suspend fun removeAllItems()
}