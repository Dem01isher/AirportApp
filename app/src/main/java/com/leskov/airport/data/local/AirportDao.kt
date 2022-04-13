package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.AirportEntity

@Dao
interface AirportDao {

    @Query("SELECT * FROM airport")
    suspend fun fetchAllData() : List<AirportEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AirportEntity)

    @Delete
    suspend fun deleteItem(item: AirportEntity)

    @Query("DELETE FROM airport")
    suspend fun removeAllItems()
}