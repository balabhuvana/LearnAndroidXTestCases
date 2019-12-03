package com.example.learnandroidxtestcases.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.activity_custom_text_matcher.*
import java.util.*


class CustomTextMatcherActivity : AppCompatActivity() {

    @VisibleForTesting
    val COFFEE_PREPARATIONS =
        Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew")

    @VisibleForTesting
    val VALID_ENDING = "coffee"

    private var mInputText: EditText? = null
    private var mSuccessView: View? = null
    private var mErrorView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_text_matcher)


        mInputText = findViewById(R.id.editText) as EditText
        mSuccessView = findViewById(R.id.inputValidationSuccess)
        mErrorView = findViewById(R.id.inputValidationError)

        btnValidate.setOnClickListener {
            val inputText = mInputText!!.text.toString()
            showResult(validateText(inputText))
        }
    }


    private fun showResult(isValidResult: Boolean) {
        mSuccessView!!.visibility = if (isValidResult) View.VISIBLE else View.GONE
        mErrorView!!.visibility = if (isValidResult) View.GONE else View.VISIBLE
    }

    private fun validateText(inputText: String): Boolean { // Every input ending in VALID_ENDING will return true.
        if (inputText.toLowerCase().endsWith(VALID_ENDING)) {
            return true
        }
        // Check if the string is in the list.
        for (preparation in COFFEE_PREPARATIONS) {
            if (preparation.equals(inputText, ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}
