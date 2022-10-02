package ru.itis.persikill.androidhomeworks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itis.persikill.androidhomeworks.databinding.ActivityScareBinding

class ScareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScareBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        intent?.also {
            if (it.action == Intent.ACTION_SEND && it.type == "text/plain") {
                binding.tvMessage.text = intent.getStringExtra(Intent.EXTRA_TEXT)

            }
        }
    }
}