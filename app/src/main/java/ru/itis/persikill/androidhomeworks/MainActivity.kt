package ru.itis.persikill.androidhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import ru.itis.persikill.androidhomeworks.database.TaskDatabase
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding
import ru.itis.persikill.androidhomeworks.extentions.findController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var taskDB: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        taskDB = TaskDatabase.getInstance(applicationContext)


        navController = findController(R.id.fragment_container)

        if (savedInstanceState != null) {
            return
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (navController.currentDestination?.id == R.id.listFragment) {
            navController.popBackStack(R.id.listFragment, true)
            navController.navigate(R.id.listFragment)
        }
    }
}