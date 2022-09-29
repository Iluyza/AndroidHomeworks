package ru.itis.persikill.androidhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val mButton1 = binding.btnRed
        val mButton2 = binding.tvStatus
        var temp = true

        mButton1.setOnClickListener {
            if (temp) {
                mButton2.visibility = View.GONE
            } else {
                mButton2.visibility = View.VISIBLE
            }
            temp = !temp
        }
    }
}