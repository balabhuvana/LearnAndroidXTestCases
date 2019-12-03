package com.example.learnandroidxtestcases

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learnandroidxtestcases.activity.LearnRecyclerViewActivity
import com.example.learnandroidxtestcases.adapter.LearnRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_learn_test_list_view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LearnRecyclerViewActivityTest {

    var activityScenario: ActivityScenario<LearnRecyclerViewActivity>? = null

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(LearnRecyclerViewActivity::class.java)
    }

    @Test
    fun validateRecyclerView() {
        activityScenario?.moveToState(Lifecycle.State.STARTED)
        activityScenario?.onActivity { activity ->
            Assert.assertNotNull(activity.learnRecyclerView)
        }
    }

    @Test
    fun validateRecyclerViewClickEventOne() {
        onView(withId(R.id.learnRecyclerView))
            .perform(actionOnItemAtPosition<LearnRecyclerViewAdapter.MyViewHolder>(3, click()));
    }

    @Test
    fun validateRecyclerViewClickEventTwo() {
        onView(withId(R.id.learnRecyclerView))
            .perform(actionOnItemAtPosition<LearnRecyclerViewAdapter.MyViewHolder>(3, click()));
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        onView(withId(R.id.learnRecyclerView))
            .perform(actionOnItemAtPosition<LearnRecyclerViewAdapter.MyViewHolder>(30, click()));
        onView(withText("Anand Kumar")).check(matches(isDisplayed()))
    }

    @Test
    fun listItemNotDisplayedCompletely() {
        onView(withText(lastItemPositionThirty)).check(doesNotExist())
    }

    @Test
    fun listItemDisplayedCompletely() {
        onView(withId(R.id.learnRecyclerView)).perform(
            actionOnItemAtPosition<LearnRecyclerViewAdapter.MyViewHolder>(
                30,
                click()
            )
        )
        onView(withText(lastItemPositionThirty)).check(matches(isDisplayed()))
    }

    @Test
    fun clickInsideRecyclerViewItem() {
        onView(withId(R.id.learnRecyclerView)).perform(
            actionOnItemAtPosition<LearnRecyclerViewAdapter.MyViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.tvName)
            )
        )
    }

    companion object {
        var firstItemPositionZero: String = "Arun Kumar"
        var middleItemPositionNine: String = "Louis Kutty"
        var lastItemPositionThirty: String = "Anand Kumar"
    }
}