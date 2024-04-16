package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDAO {
    @Insert
    fun insertData(word: Word)

    @Delete
    fun deleteData(word: Word)

    @Update
    fun updateData(word: Word)

    @Query("SELECT * FROM Words")
    fun getAllData(): MutableList<Word>
}