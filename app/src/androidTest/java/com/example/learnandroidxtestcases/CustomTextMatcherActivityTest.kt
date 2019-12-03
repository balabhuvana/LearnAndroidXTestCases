package com.example.learnandroidxtestcases

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learnandroidxtestcases.activity.CustomTextMatcherActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CustomTextMatcherActivityTest {

    var activityScenario: ActivityScenario<CustomTextMatcherActivity>? = null

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(CustomTextMatcherActivity::class.java)
    }

    @Test
    fun validateGoodChoice() {
        onView(withId(R.id.editText)).perform(typeText("Espresso"), closeSoftKeyboard())
        onView(withId(R.id.btnValidate)).perform(click())
        onView(withText("Good choice!")).check(matches(isDisplayed()))
    }

    @Test
    fun validateBadChoice() {
        onView(withId(R.id.editText)).perform(typeText("Anand"), closeSoftKeyboard())
        onView(withId(R.id.btnValidate)).perform(click())
        onView(withText("Bad choice!")).check(matches(isDisplayed()))
    }
}