package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.AirportEntity

@Dao
interface AirportDao {

    @Query("SELECT * FROM airport")
    fun fetchAllData() : List<AirportEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: AirportEntity)

    @Delete
    fun deleteItem(item: AirportEntity)

    @Query("DELETE FROM airport")
    fun removeAllItems()
}