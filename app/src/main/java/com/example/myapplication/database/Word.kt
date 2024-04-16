package com.example.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Words")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val definitions: String,
    val date: Date,
)