package org.hierax.wordsroomlab

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.hierax.wordsroomlab.repository.Word
import org.hierax.wordsroomlab.repository.WordRepository

class WordViewModel(private val wordRepository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = wordRepository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }

}

class WordViewModelFactory(private val wordRepository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(wordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}