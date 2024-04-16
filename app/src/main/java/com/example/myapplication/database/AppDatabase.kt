package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class Converters {
    @TypeConverter
    fun from(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun to(date: Date?): Long? {
        return date?.time
    }
}

@TypeConverters(Converters::class)
@Database(entities = [Word::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getWordDAO(): WordDAO

    companion object {
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "app_db")
                    .allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}