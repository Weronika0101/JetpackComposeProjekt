package com.example.aandroidprojekt.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert
    suspend fun insertAll(contacts: List<DBItem>)
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData(): MutableList<DBItem>

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData3(): Flow<List<DBItem>> // flow

    @Query("DELETE FROM item_table")
    fun deleteAll()
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(DBItem: DBItem) : Long
    @Delete
    fun delete(DBItem: DBItem) : Int
    @Update
    fun update(DBItem: DBItem): Int
    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun getItemById(itemId: Long): DBItem?
    @Query("DELETE FROM item_table")
    suspend fun dropDatabase()

}