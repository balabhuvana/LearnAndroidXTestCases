package com.example.learnandroidxtestcases.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidxtestcases.R

class SuggestedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggested)

        val oceanListAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.bodies_of_water)
        )

        /*
            Set a threshold then only auto complete will work
         */
        val tvAutoCompleteOceanList = findViewById<AutoCompleteTextView>(R.id.tvAutoCompleteOcean)
        tvAutoCompleteOceanList.setAdapter(oceanListAdapter)

    }
}
