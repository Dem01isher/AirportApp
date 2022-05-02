package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.AirplaneEntity
import java.util.ArrayList

@Dao
interface AirplaneDao {
    @Query("SELECT * FROM airplane")
    fun fetchAllData() : LiveData<List<AirplaneEntity?>>

    @Query("SELECT * FROM airplane WHERE producer LIKE:producer")
    fun findItemByKey(producer: String) : AirplaneEntity

    @Query("SELECT * FROM airplane WHERE producer LIKE '%' || :searchText|| '%' OR model LIKE '%' || :searchText|| '%' ")
    fun searchData(searchText: String) : LiveData<List<AirplaneEntity?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AirplaneEntity::class)
    suspend fun insertItem(item: AirplaneEntity)

    @Delete(entity = AirplaneEntity::class)
    suspend fun deleteItem(item: AirplaneEntity)

    @Update(entity = AirplaneEntity::class)
    suspend fun updateItem(item: AirplaneEntity)

    @Query("DELETE FROM airplane")
    suspend fun removeAllItems()
}