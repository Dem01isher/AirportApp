package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.AirportEntity
import java.util.ArrayList

@Dao
interface AirportDao {

    @Query("SELECT * FROM airport")
    fun fetchAllData() : LiveData<List<AirportEntity?>>

    @Query("SELECT * FROM airport WHERE city LIKE:city")
    fun findItemByKey(city: String) : AirportEntity

    @Query("SELECT * FROM airport WHERE countryLocation LIKE '%' || :searchText|| '%' OR countOfTerminals LIKE '%' || :searchText|| '%' ")
    fun searchData(searchText: String) : LiveData<List<AirportEntity?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AirportEntity::class)
    suspend fun insertItem(item: AirportEntity)

    @Delete(entity = AirportEntity::class)
    suspend fun deleteItem(item: AirportEntity)

    @Update(entity = AirportEntity::class)
    suspend fun updateItem(item: AirportEntity)

    @Query("DELETE FROM airport")
    suspend fun removeAllItems()
}