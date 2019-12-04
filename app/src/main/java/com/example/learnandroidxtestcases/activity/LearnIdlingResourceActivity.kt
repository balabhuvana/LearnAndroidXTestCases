package com.example.learnandroidxtestcases.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandroidxtestcases.idlingresource.EspressoIdlingResource
import com.example.learnandroidxtestcases.R
import com.example.learnandroidxtestcases.adapter.LearnRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_learn_test_list_view.*

class LearnIdlingResourceActivity : AppCompatActivity() {

    var linearLayoutManager: LinearLayoutManager? = null
    var learnRecyclerViewAdapter: LearnRecyclerViewAdapter? = null
    private lateinit var nameList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_idling_resource)

        nameList = ArrayList()
        nameList.add("Arun Kumar")
        nameList.add("Deva Kumar")
        nameList.add("Bhuvana")
        nameList.add("Karthik")
        nameList.add("Kannan")
        nameList.add("Subash")
        nameList.add("Satheesh")
        nameList.add("Mahesh")
        nameList.add("Monika")
        nameList.add("Louis Kutty")
        nameList.add("11")
        nameList.add("12")
        nameList.add("13")
        nameList.add("14")
        nameList.add("15")
        nameList.add("16")
        nameList.add("17")
        nameList.add("18")
        nameList.add("19")
        nameList.add("20")
        nameList.add("21")
        nameList.add("22")
        nameList.add("23")
        nameList.add("24")
        nameList.add("25")
        nameList.add("26")
        nameList.add("27")
        nameList.add("28")
        nameList.add("29")
        nameList.add("30")
        nameList.add("Anand Kumar")

        linearLayoutManager = LinearLayoutManager(this)

        EspressoIdlingResource.increment()
        Handler().postDelayed(Runnable {
            learnRecyclerViewAdapter = LearnRecyclerViewAdapter(this, nameList)
            learnRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = learnRecyclerViewAdapter
            }
            EspressoIdlingResource.decrement()
        }, 15000)

    }

}
