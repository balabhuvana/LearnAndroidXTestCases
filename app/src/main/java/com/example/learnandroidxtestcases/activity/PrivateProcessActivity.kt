package com.example.learnandroidxtestcases.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.learnandroidxtestcases.R
import com.example.learnandroidxtestcases.util.Util.setCurrentRunningProcess


/**
 * Activity running in a private process which is manually defined in the AndroidManifest.xml using
 * the android:process attribute.
 *
 *
 *
 * From D.A.C: "If the name assigned to this attribute begins with a colon (':'), a new process,
 * private to the application, is created when it's needed and the activity runs in that process."
 */
class PrivateProcessActivity : Activity(),
    AdapterView.OnItemClickListener {
    private var privateProcessNameTextView: TextView? = null
    private var selectedListItemTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_process)
        privateProcessNameTextView = findViewById(R.id.textPrivateProcessName)

        setCurrentRunningProcess(privateProcessNameTextView!!, this)

        selectedListItemTextView = findViewById(R.id.selectedListItemText)
        val listView = findViewById<ListView>(R.id.list)
        val listItems =
            resources.getStringArray(R.array.list_items)
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
        listView.onItemClickListener = this
    }

    fun onBtnClick(view: View?) {
        val v = findViewById<TextView>(R.id.displayTextView)
        v.setText(R.string.button_clicked)
    }

    override fun onItemClick(
        parent: AdapterView<*>?,
        view: View,
        position: Int,
        id: Long
    ) {
        selectedListItemTextView!!.text = String.format(
            getString(R.string.list_selection),
            (view as TextView).text
        )
    }
}