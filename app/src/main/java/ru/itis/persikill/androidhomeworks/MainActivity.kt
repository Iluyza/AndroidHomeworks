package ru.itis.persikill.androidhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButton1 = findViewById<EditText>(R.id.et_red)
        val mButton2: TextView = findViewById(R.id.tv_status)

        val temp = false
        mButton1.setOnClickListener {
            if (!temp) {
                mButton2.visibility = View.GONE
            }
        }
    }
}