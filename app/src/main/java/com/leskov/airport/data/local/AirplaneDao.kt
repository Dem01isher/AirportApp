package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.entity.AirplaneEntity
import java.util.ArrayList

@Dao
interface AirplaneDao {
    @Query("SELECT * FROM airplane")
    fun fetchAllData() : List<AirplaneEntity?>

    @Query("SELECT * FROM airplane WHERE producer LIKE :searchText OR model LIKE :searchText ")
    suspend fun searchData(searchText: String) : List<AirplaneEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AirplaneEntity::class)
    suspend fun insertItem(item: AirplaneEntity)

    @Delete(entity = AirplaneEntity::class)
    suspend fun deleteItem(item: AirplaneEntity)

    @Update(entity = AirplaneEntity::class)
    suspend fun updateItem(item: AirplaneEntity)

    @Query("DELETE FROM airplane")
    suspend fun removeAllItems()
}