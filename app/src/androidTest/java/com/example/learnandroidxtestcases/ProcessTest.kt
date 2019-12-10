package com.example.learnandroidxtestcases

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.learnandroidxtestcases.activity.DefaultProcessActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ProcessTest {

    private val TAG = "ProcessTest"

    @Before
    fun launchActivity() {
        ActivityScenario.launch<DefaultProcessActivity>(DefaultProcessActivity::class.java)
    }

    @Test
    fun verifyAssertingOnViewInRemoteProcessIsSuccessful() {
        Log.d(TAG, "Checking main process name...")
        Espresso.onView(withId(R.id.textNamedProcess)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    Matchers.`is`<String>(DEFAULT_PROC_NAME)
                )
            )
        )
        Log.d(
            TAG,
            "Starting activity in a secondary process..."
        )
        Espresso.onView(withId(R.id.startActivityBtn)).perform(ViewActions.click())
        Log.d(
            TAG,
            "Checking private process name..."
        )
        Espresso.onView(withId(R.id.textPrivateProcessName))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        Matchers.`is`<String>(
                            DEFAULT_PROC_NAME + ":PID2"
                        )
                    )
                )
            )
        Log.d(
            TAG,
            "Clicking list item in private process activity..."
        )
        Espresso.onData(
            Matchers.allOf(
                Matchers.instanceOf<Any>(String::class.java),
                Matchers.`is`("Doppio")
            )
        ).perform(ViewActions.click())
        Log.d(TAG, "Check selected text appears...")
        Espresso.onView(withId(R.id.selectedListItemText))
            .check(ViewAssertions.matches(ViewMatchers.withText("Selected: Doppio")))
    }

    companion object {
        private const val TAG = "ExampleInstrumentedTest"
        private const val DEFAULT_PROC_NAME =
            "com.example.learnandroidxtestcases"
    }
}

