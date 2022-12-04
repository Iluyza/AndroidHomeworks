package ru.itis.persikill.androidhomeworks

import android.app.Notification.EXTRA_PROGRESS
import android.nfc.NfcAdapter.EXTRA_ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding
import ru.itis.persikill.androidhomeworks.service.MusicsFragment
import ru.itis.persikill.androidhomeworks.service.OneMusicFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        var newFragment: Fragment? = null
        if (intent.getIntExtra(EXTRA_PROGRESS, -1) != -1){
            newFragment = OneMusicFragment()
            val bundle = Bundle().apply {
                putInt(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
            newFragment.arguments = bundle
        } else {
            newFragment = MusicsFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newFragment)
            .commit()
    }
}