package com.example.aandroidprojekt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [DBItem::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao?
    companion object {
        private var DB_INSTANCE: MyDatabase? = null
        @Synchronized
        open fun getDatabase(context: Context): MyDatabase? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = databaseBuilder(context.applicationContext,
                    MyDatabase::class.java,
                    "item_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE
        }
    }
}