package com.example.learnandroidxtestcases.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.activity_edit_text_base.*

class EditTextBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_base)

        btnChangeText.setOnClickListener {
            tvChangedText.text = etTypeText.text.toString()
        }

        btnNextScreenChangeText.setOnClickListener {
            var intent = Intent(this, EdittextNextActivity::class.java).apply {
                putExtra(EdittextNextActivity.changeText, etTypeText.text.toString())
            }
            startActivity(intent)
        }
    }
}
