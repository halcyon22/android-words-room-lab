package org.hierax.wordsroomlab

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import org.hierax.wordsroomlab.databinding.ActivityAddWordBinding

class AddWordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        val binding = ActivityAddWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.textInputWord.text)) {
                setResult(EMPTY_WORD, replyIntent)
            } else {
                val word = binding.textInputWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(VALID_WORD, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "org.hierax.wordsroomlab.AddWordActivity.REPLY"
        const val VALID_WORD = 1
        const val EMPTY_WORD = 2
    }
}