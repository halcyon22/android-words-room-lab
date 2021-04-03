package org.hierax.wordsroomlab

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.hierax.wordsroomlab.databinding.ActivityMainBinding
import org.hierax.wordsroomlab.repository.Word

class MainActivity : AppCompatActivity() {
    private val addWordLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(), this::addWordCallback)

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        wordViewModel.allWords.observe(this, { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.buttonAdd.setOnClickListener {
            addWordLauncher.launch(Intent(this@MainActivity, AddWordActivity::class.java))
        }
    }

    private fun addWordCallback(result : ActivityResult) {
        when (result.resultCode) {
            AddWordActivity.VALID_WORD -> {
                result.data?.getStringExtra(AddWordActivity.EXTRA_REPLY)?.let {
                    wordViewModel.insert(Word(it))
                }
            }
            else -> {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}