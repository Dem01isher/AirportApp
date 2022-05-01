package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.AirCompanyEntity
import java.util.ArrayList

@Dao
interface AirCompanyDao {

    @Query("SELECT * FROM airCompany")
    fun fetchAllData() : List<AirCompanyEntity?>

    @Query("SELECT * FROM airCompany WHERE nameOf LIKE:name")
    fun findItemByKey(name: String) : AirCompanyEntity

    @Query("SELECT * FROM airCompany WHERE officeLocation LIKE '%' || :searchText|| '%' OR countOfLanes LIKE '%' || :searchText|| '%' ")
    suspend fun searchData(searchText: String) : List<AirCompanyEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AirCompanyEntity::class)
    suspend fun insertItem(item: AirCompanyEntity)

    @Delete(entity = AirCompanyEntity::class)
    suspend fun deleteItem(item: AirCompanyEntity)

    @Update(entity = AirCompanyEntity::class)
    suspend fun updateItem(item: AirCompanyEntity)

    @Query("DELETE FROM airCompany")
    suspend fun removeAllItems()
}