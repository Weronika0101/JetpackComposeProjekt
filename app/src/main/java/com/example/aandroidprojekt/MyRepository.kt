package com.example.aandroidprojekt

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.aandroidprojekt.data.DBItem
import com.example.aandroidprojekt.data.MyDao
import com.example.aandroidprojekt.data.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MyRepository(context: Context) {
    private var dataList: MutableList<DBItem>? = null //tu tez bedzie livedata lub flow
    private var dataList2: LiveData<List<DBItem>>? = null //tu tez bedzie livedata lub flow
    private var dataList3: Flow<List<DBItem>>? = null //tu tez bedzie livedata lub flow
    private var myDao: MyDao
    private var db: MyDatabase


    companion object {
        private var R_INSTANCE: MyRepository? = null
        fun getinstance(context: Context): MyRepository {
            if (R_INSTANCE == null) {
                R_INSTANCE = MyRepository(context)
            }
            return R_INSTANCE as MyRepository
        }
    }

    init {
        db = MyDatabase.getDatabase(context)!!
        myDao = db.myDao()!!


    }
    suspend fun insertAll(contacts: List<DBItem>) = withContext(Dispatchers.IO) {
        myDao.insertAll(contacts)
    }
    suspend fun insertItem(item: DBItem) =withContext(Dispatchers.IO){
        myDao.insert(item)
    }

    fun getData(): MutableList<DBItem>? {
        dataList = myDao.getAllData()
        return dataList
    }


    fun getData3(): Flow<List<DBItem>>? {
        dataList3 = myDao.getAllData3()
        return dataList3
    }

    fun addItem(item: DBItem?): Boolean {
        if (item?.let { myDao.insert(it) }!! >= 0) return true
        else return false
    }

    fun deleteItem(item: DBItem): Boolean {
        if (item?.let { myDao.delete(it) }!! > 0) return true
        else return false
    }

        fun updateItem(item: DBItem): Boolean {
            val result = myDao.update(item)
            return result > 0
        }

        fun getDataById(id: Long): DBItem? {
            return myDao.getItemById(id)
        }

        suspend fun dropDatabase() = withContext(Dispatchers.IO) {
            myDao.dropDatabase()
        }
    }