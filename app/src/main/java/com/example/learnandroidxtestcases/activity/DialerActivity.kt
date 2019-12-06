package com.example.learnandroidxtestcases.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.activity_dialer.*

class DialerActivity : AppCompatActivity() {

    private val REQUEST_CODE_PICK = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialer)

    }

    fun onCall(view: View?) {
        val hasCallPhonePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
        if (hasCallPhonePermission) startActivity(createCallIntentFromNumber()) else Toast.makeText(
            this,
            R.string.dial_permission_not_available,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onPickContact(view: View?) {
        val pickContactIntent = Intent(this, ContactsActivity::class.java)
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK)
    }

    private fun createCallIntentFromNumber(): Intent? {
        val intentToCall = Intent(Intent.ACTION_CALL)
        val number = edit_text_caller_number!!.text.toString()
        intentToCall.data = Uri.parse("tel:$number")
        return intentToCall
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == Activity.RESULT_OK) {
                edit_text_caller_number!!.setText(
                    data?.extras
                        ?.getString(ContactsActivity.KEY_PHONE_NUMBER)
                )
            }
        }
    }

    companion object {
        const val VALID_PHONE_NUMBER = "123-345-6789"
    }

}
