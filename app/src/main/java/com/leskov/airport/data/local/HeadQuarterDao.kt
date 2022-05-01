package com.leskov.airport.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leskov.airport.domain.entity.HeadQuarterEntity
import java.util.ArrayList

@Dao
interface HeadQuarterDao {

    @Query("SELECT * FROM headquarter")
    fun fetchAllData() : List<HeadQuarterEntity?>

    @Query("SELECT * FROM headquarter WHERE numberOf LIKE:numberOf")
    fun findItemByType(numberOf: Int) : HeadQuarterEntity

    @Query("SELECT * FROM headquarter WHERE countOfLevels LIKE '%' || :searchText|| '%' OR numberOfBeds LIKE '%' || :searchText|| '%' ")
    suspend fun searchData(searchText: String) : List<HeadQuarterEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = HeadQuarterEntity::class)
    suspend fun insertItem(item: HeadQuarterEntity)

    @Delete(entity = HeadQuarterEntity::class)
    suspend fun deleteItem(item: HeadQuarterEntity)

    @Update(entity = HeadQuarterEntity::class)
    suspend fun updateItem(item: HeadQuarterEntity)

    @Query("DELETE FROM headquarter")
    suspend fun removeAllItems()
}