package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.InsuranceEntity
import java.util.ArrayList

@Dao
interface InsuranceDao {

    @Query("SELECT * FROM insurance")
    fun fetchAllData() : LiveData<List<InsuranceEntity?>>

    @Query("SELECT * FROM insurance WHERE serviceName LIKE:serviceName")
    fun findItemByKey(serviceName: String) : InsuranceEntity

    @Query("SELECT * FROM insurance WHERE serviceName LIKE '%' || :searchText|| '%' OR term LIKE '%' || :searchText|| '%' ")
    fun searchData(searchText: String) : LiveData<List<InsuranceEntity?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = InsuranceEntity::class)
    suspend fun insertItem(item: InsuranceEntity)

    @Delete(entity = InsuranceEntity::class)
    suspend fun deleteItem(item: InsuranceEntity)

    @Update(entity = InsuranceEntity::class)
    suspend fun updateItem(item: InsuranceEntity)

    @Query("DELETE FROM insurance")
    suspend fun removeAllItems()
}