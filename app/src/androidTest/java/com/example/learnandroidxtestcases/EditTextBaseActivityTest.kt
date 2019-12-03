package com.example.learnandroidxtestcases

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learnandroidxtestcases.activity.EditTextBaseActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditTextBaseActivityTest {

    private var activityScenario: ActivityScenario<EditTextBaseActivity>? = null

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(EditTextBaseActivity::class.java)
    }

    @Test
    fun validateEditTextWithKeyBoard() {
        onView(withId(R.id.etTypeText)).perform(
            typeText(sample_string),
            ViewActions.closeSoftKeyboard()
        )
    }

    @Test
    fun validateEditTextWith() {
        onView(withId(R.id.etTypeText)).perform(
            typeText(sample_string),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.btnChangeText)).perform(click())
        onView(withId(R.id.tvChangedText)).check(matches(withText(sample_string)))
    }

    @Test
    fun validateTextChangeInNextScreen() {
        onView(withId(R.id.etTypeText)).perform(typeText(sample_string), closeSoftKeyboard())
        onView(withId(R.id.btnNextScreenChangeText)).perform(click())
        onView(withId(R.id.tvChangedTextNewScreen)).check(matches(withText(sample_string)))
    }

    companion object {
        var sample_string = "Espresso"
    }
}