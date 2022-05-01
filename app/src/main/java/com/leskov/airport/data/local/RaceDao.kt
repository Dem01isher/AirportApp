package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.RaceEntity
import java.util.ArrayList

@Dao
interface RaceDao {

    @Query("SELECT * FROM race")
    fun fetchAllData() : List<RaceEntity?>

    @Query("SELECT * FROM race WHERE typeOfRace LIKE:typeOfRace")
    fun findItemByKey(typeOfRace: String) : RaceEntity

    @Query("SELECT * FROM race WHERE typeOfRace LIKE '%' || :searchText|| '%' OR numberOfRace LIKE '%' || :searchText|| '%' ")
    suspend fun searchData(searchText: String) : List<RaceEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = RaceEntity::class)
    suspend fun insertItem(item: RaceEntity)

    @Delete(entity = RaceEntity::class)
    suspend fun deleteItem(item: RaceEntity)

    @Update(entity = RaceEntity::class)
    suspend fun updateItem(item: RaceEntity)

    @Query("DELETE FROM race")
    suspend fun removeAllItems()
}