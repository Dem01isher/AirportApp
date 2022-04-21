package com.leskov.airport.data.local

import androidx.room.*
import com.leskov.airport.domain.entity.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM team")
    fun fetchAllData() : List<TeamEntity?>

    @Query("SELECT * FROM team WHERE countOfPeople LIKE :searchText OR countOfPilots LIKE :searchText")
    suspend fun searchData(searchText: String) : List<TeamEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = TeamEntity::class)
    suspend fun insertItem(item: TeamEntity)

    @Delete(entity = TeamEntity::class)
    suspend fun deleteItem(item: TeamEntity)

    @Update(entity = TeamEntity::class)
    suspend fun updateItem(item: TeamEntity)

    @Query("DELETE FROM team")
    suspend fun removeAllItems()
}