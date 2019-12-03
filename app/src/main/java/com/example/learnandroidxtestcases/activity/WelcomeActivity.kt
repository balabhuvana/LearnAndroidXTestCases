package com.example.learnandroidxtestcases.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnToastMessage.setOnClickListener {
            Toast.makeText(this, this.getString(R.string.welcome_screen), Toast.LENGTH_LONG).show()
        }

        btnLaunchHome.setOnClickListener {
            var launchHomeIntent = Intent(this, HomeActivity::class.java)
            startActivity(launchHomeIntent)
        }
    }
}
