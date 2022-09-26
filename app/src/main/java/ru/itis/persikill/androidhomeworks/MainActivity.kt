package ru.itis.persikill.androidhomeworks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//    fun cleanPhoto(view: View) {
//        view.setOnClickListener() {
//            var v : View = findViewById(R.id.iv_main)
//            v.setVisibility(v.GONE)
//        }
//    }

        val mButton1 = findViewById<Button>(R.id.btn_red)
        val mButton2: ImageView = findViewById(R.id.iv_main)

        var temp = false
        mButton1.setOnClickListener {
            if (!temp) {
                mButton2.visibility = View.INVISIBLE
            } else {
                mButton2.visibility = View.VISIBLE
            }
            temp = !temp
        }
    }
}