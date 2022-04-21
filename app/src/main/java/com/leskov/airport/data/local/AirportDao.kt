package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.entity.AirportEntity
import java.util.ArrayList

@Dao
interface AirportDao {

    @Query("SELECT * FROM airport")
    fun fetchAllData() : List<AirportEntity?>

    @Query("SELECT * FROM airport WHERE title LIKE :searchText OR title LIKE :searchText OR city LIKE :searchText")
    suspend fun searchData(searchText: String) : List<AirportEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AirportEntity::class)
    suspend fun insertItem(item: AirportEntity)

    @Delete(entity = AirportEntity::class)
    suspend fun deleteItem(item: AirportEntity)

    @Update(entity = AirportEntity::class)
    suspend fun updateItem(item: AirportEntity)

    @Query("DELETE FROM airport")
    suspend fun removeAllItems()
}