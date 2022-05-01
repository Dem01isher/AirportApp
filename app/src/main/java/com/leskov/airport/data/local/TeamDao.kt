package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.TeamEntity
import java.util.ArrayList

@Dao
interface TeamDao {

    @Query("SELECT * FROM team")
    fun fetchAllData() : List<TeamEntity?>

    @Query("SELECT * FROM team WHERE countOfPeople LIKE:countOfPeople")
    fun findItemByKey(countOfPeople: Int) : TeamEntity

    @Query("SELECT * FROM team WHERE countOfPilots LIKE '%' || :searchText|| '%' OR numberOf LIKE '%' || :searchText|| '%' ")
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