package com.example.myapplication.model

import android.content.Context
import com.example.myapplication.database.AppDataBase
import com.example.myapplication.database.Word

class MainModel(context: Context) {
    val localDB = AppDataBase.getInstance(context)

    fun getWordList(): MutableList<Word> {
        return localDB.getWordDAO().getAllData()
    }

    fun addWord(word: Word) {
        localDB.getWordDAO().insertData(word)
    }

    fun updateWord(word: Word) {
        localDB.getWordDAO().updateData(word)
    }

    fun deleteWord(word: Word) {
        localDB.getWordDAO().deleteData(word)
    }
}