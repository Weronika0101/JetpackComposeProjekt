package com.example.aandroidprojekt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "item_table")
data class DBItem(
        @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var text_main : String = "",
        @ColumnInfo(name = "sport")
    var text_sport : String ="",
        @ColumnInfo(name = "beginner")
    var item_beginner : Boolean = Random.nextBoolean(),
        @ColumnInfo(name = "rating")
    var item_trudnosc: Int = Random.nextInt(0, 5),
        @ColumnInfo(name = "opis")
    var item_opis: String = "Default opis",
        @ColumnInfo(name = "typ")
    var item_type : Int = Random.nextInt(0, 4),
)

