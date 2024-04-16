package com.example.myapplication.viewmodel

import android.content.Context
import com.example.myapplication.database.Word
import com.example.myapplication.model.MainModel

class MainViewModel(context: Context) {
    private val model = MainModel(context)

    fun filter(query: String): List<Word> {
        val list = getWords()
        when (query) {
            "All" -> return list
            "A-Z" -> return list.sortedBy { it.word }
            "Z-A" -> return list.sortedByDescending { it.word }
            "Newest" -> return list.sortedBy { it.date }
            "Oldest" -> return list.sortedByDescending { it.date }
        }
        return emptyList()
    }

    fun addWord(word: Word) {
        model.addWord(word)
    }

    fun deleteWord(word: Word) {
        return model.deleteWord(word)
    }

    fun updateWord(word: Word) {
        return model.updateWord(word)
    }

    fun getWords(): List<Word> {
        return model.getWordList()
    }
}