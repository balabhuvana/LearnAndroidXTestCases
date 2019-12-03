package com.example.learnandroidxtestcases

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learnandroidxtestcases.idlingresource.EspressoIdlingResource.idlingResource
import com.example.learnandroidxtestcases.activity.LearnIdlingResourceActivity
import com.example.learnandroidxtestcases.adapter.LearnRecyclerViewAdapter
import com.example.learnandroidxtestcases.idlingresource.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LearnIdlingResourceActivityTest {

    var activityScenario: ActivityScenario<LearnIdlingResourceActivity>? = null

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(LearnIdlingResourceActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun validateRecyclerView() {
        onView(withId(R.id.learnRecyclerView)).perform(
            actionOnItemAtPosition<LearnRecyclerViewAdapter.MyViewHolder>(
                0,
                click()
            )
        )
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

}