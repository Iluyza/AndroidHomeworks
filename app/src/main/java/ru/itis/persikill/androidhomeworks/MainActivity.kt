package ru.itis.persikill.androidhomeworks

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var buttonSendPicture: Button? = null
    private var buttonSendQuote: Button? = null
    private var buttonSendInternet: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonSendQuote = binding.btnShareQuote
        buttonSendInternet = binding.btnFindInternet
        buttonSendPicture = binding.btnSharePictures

        buttonSendInternet!!.setOnClickListener {
            dialPhoneNumber()
        }

        buttonSendPicture!!.setOnClickListener {
            openURL()
        }

        buttonSendQuote!!.setOnClickListener {
            shareQuote()
        }
    }

    fun openURL() {
        val url = "https://pinterest.com"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(intent)
    }

//    fun sendEmail() {
//        val recipients = arrayOf("asylgareeva03@inbox.ru")
//        val subject = "Hi, how are you!"
//        val content = "This is my test email"
//        var intentEmail: Intent = Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
//        intentEmail.putExtra(Intent.EXTRA_EMAIL, recipients);
//        intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intentEmail.putExtra(Intent.EXTRA_TEXT, content);
//        intentEmail.setType("text/plain");
//
//        startActivity(Intent.createChooser(intentEmail, "Choose an email client from..."));
//    }

    fun dialPhoneNumber() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$9656615965")
        }
        val chooserIntent = Intent.createChooser(intent,"h")
        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareQuote() {
        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT,(binding.tvMotivationText).text)
        intent.setType("text/plan")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}