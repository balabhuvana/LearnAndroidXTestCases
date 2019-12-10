package com.example.learnandroidxtestcases.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R
import com.example.learnandroidxtestcases.util.Util.setCurrentRunningProcess
import kotlinx.android.synthetic.main.activity_default_process.*

class DefaultProcessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_process)
        setCurrentRunningProcess(textNamedProcess, this)
    }

    fun onStartActivityBtnClick(view: View?) {
        val intent = Intent(this, PrivateProcessActivity::class.java)
        startActivity(intent)
    }
}
