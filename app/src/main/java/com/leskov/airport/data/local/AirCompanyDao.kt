package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.entity.AirCompanyEntity

@Dao
interface AirCompanyDao {

    @Query("SELECT * FROM airCompany")
    fun fetchAllData() : List<AirCompanyEntity?>

    @Query("SELECT * FROM airCompany WHERE nameOf LIKE :searchText OR officeLocation LIKE :searchText")
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