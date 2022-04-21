package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.entity.RouteEntity

@Dao
interface RouteDao {

    @Query("SELECT * FROM route")
    fun fetchAllData() : List<RouteEntity?>

    @Query("SELECT * FROM route WHERE destinationCountry LIKE :searchText OR departureCountry LIKE :searchText")
    suspend fun searchData(searchText: String) : List<RouteEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = RouteEntity::class)
    suspend fun insertItem(item: RouteEntity)

    @Delete(entity = RouteEntity::class)
    suspend fun deleteItem(item: RouteEntity)

    @Update(entity = RouteEntity::class)
    suspend fun updateItem(item: RouteEntity)

    @Query("DELETE FROM route")
    suspend fun removeAllItems()
}