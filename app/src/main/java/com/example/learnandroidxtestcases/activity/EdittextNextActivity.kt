package com.example.learnandroidxtestcases.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.activity_edittext_next.*

class EdittextNextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext_next)
        tvChangedTextNewScreen.text = intent.getStringExtra(changeText)
    }

    companion object {
        var changeText: String = "change_text"
    }
}
