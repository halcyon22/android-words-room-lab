package org.hierax.wordsroomlab.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// This is overkill when there's a single data source
class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getSortedWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insertWord(word)
    }
}