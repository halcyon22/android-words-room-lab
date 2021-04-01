package org.hierax.wordsroomlab.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("select * from word_table order by word asc")
    fun getSortedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Query("delete from word_table")
    suspend fun deleteAllWords()
}