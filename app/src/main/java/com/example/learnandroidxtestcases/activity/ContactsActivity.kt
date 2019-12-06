package com.example.learnandroidxtestcases.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R


class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setResult(Activity.RESULT_OK, createResultData(DialerActivity.VALID_PHONE_NUMBER))
        finish()
    }

    @VisibleForTesting
    fun createResultData(phoneNumber: String?): Intent? {
        val resultData = Intent()
        resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber)
        return resultData
    }

    companion object{
        const val KEY_PHONE_NUMBER = "key_phone_number"

        @VisibleForTesting
        fun createResultData(phoneNumber: String?): Intent? {
            val resultData = Intent()
            resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber)
            return resultData
        }
    }
}
