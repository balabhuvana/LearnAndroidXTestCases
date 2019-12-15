package com.example.learnandroidxtestcases

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.learnandroidxtestcases.activity.SuggestedActivity
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SuggestedActivityTest {

    private var mActivity: SuggestedActivity? = null

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(
        SuggestedActivity::class.java
    )

    @Before
    fun setActivity() {
        mActivity = mActivityRule.activity
    }

    @Test
    fun autoCompleteTextView_twoSuggestions() {

        onView(withId(R.id.tvAutoCompleteOcean))
            .perform(typeText("A"), closeSoftKeyboard())

        onView(withText("Atlantic Ocean"))
            .inRoot(
                withDecorView(
                    not(
                        `is`(
                            mActivity!!.window.decorView
                        )
                    )
                )
            )
            .check(matches(isDisplayed()))

        onView(withText("Arabic Ocean"))
            .inRoot(
                withDecorView(
                    not(
                        `is`(
                            mActivity!!.window.decorView
                        )
                    )
                )
            )
            .check(matches(isDisplayed()))

    }

    @Test
    fun autoCompleteView_twoSuggestion() {

        onView(withId(R.id.tvAutoCompleteOcean)).perform(
            typeText("South"),
            closeSoftKeyboard()
        )

        onView(withText("South China Sea")).inRoot(
            withDecorView(
                not(
                    `is`(
                        mActivity!!.window.decorView
                    )
                )
            )
        ).check(matches(isDisplayed()))

        onView(withText("Southern Ocean")).inRoot(
            withDecorView(
                not(
                    `is`(
                        mActivity!!.window.decorView
                    )
                )
            )
        ).check(matches(isDisplayed()))

    }

    @Test
    fun autoCompleteView_SelectedText() {

        onView(withId(R.id.tvAutoCompleteOcean))
            .perform(typeText("S"), closeSoftKeyboard())

        onData(
            allOf(
                instanceOf<Any>(String::class.java),
                `is`("Baltic Sea")
            )
        )
            .inRoot(withDecorView(not<View>(`is`(mActivity!!.window.decorView))))
            .perform(click())

        onView(withId(R.id.tvAutoCompleteOcean))
            .check(matches(withText("Baltic Sea")))
    }
}