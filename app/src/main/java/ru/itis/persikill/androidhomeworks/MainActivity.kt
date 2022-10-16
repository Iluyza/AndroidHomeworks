package ru.itis.persikill.androidhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val containerID = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            return
        }
        supportFragmentManager.beginTransaction()
            .add(
                containerID,
                MainFragment.newInstance(Bundle()),
                MainFragment.MAIN_FRAGMENT_TAG
            ).commit()
    }
}