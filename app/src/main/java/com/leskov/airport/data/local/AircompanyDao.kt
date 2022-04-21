package com.leskov.airport.data.local

//@Dao
//interface AircompanyDao {
//
//    @Query("SELECT * FROM aircompany")
//    fun fetchAllData() : List<AircompanyEntity>
//
//    @Query("SELECT * FROM aircompany WHERE nameOfCompany LIKE :searchText OR officeLocation LIKE :searchText ")
//    suspend fun searchData(searchText: String) : List<AircompanyEntity>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = AircompanyEntity::class)
//    suspend fun insertItem(item: AircompanyEntity)
//
//    @Delete(entity = AircompanyEntity::class)
//    suspend fun deleteItem(item: AircompanyEntity)
//
//    @Update(entity = AircompanyEntity::class)
//    suspend fun updateItem(item: AircompanyEntity)
//
//    @Query("DELETE FROM aircompany")
//    suspend fun removeAllItems()
//}