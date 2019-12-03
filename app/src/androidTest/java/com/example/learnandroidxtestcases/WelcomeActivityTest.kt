package com.example.learnandroidxtestcases

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learnandroidxtestcases.activity.HomeActivity
import com.example.learnandroidxtestcases.activity.WelcomeActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeActivityTest {

    var activityScenario: ActivityScenario<WelcomeActivity>? = null

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(WelcomeActivity::class.java)
    }

    @Test
    fun validateWelcomeText_FromTextView() {
        activityScenario?.onActivity { activity ->
            Assert.assertNotNull(activity.tvWelcomeScreen)
            var welcome_text = activity.tvWelcomeScreen.text.toString()
            Assert.assertEquals("Welcome Screen", welcome_text)
        }
    }

    @Test
    fun validateToastMessage_WithButtonClick() {
        activityScenario?.moveToState(Lifecycle.State.STARTED)
        activityScenario?.onActivity { activity ->
            Assert.assertNotNull(activity.btnToastMessage)
            Assert.assertEquals("Toast Button", activity.btnToastMessage.text)
            activity.btnToastMessage.performClick()
        }
    }

    @Test
    fun validateToastMessage_WithButtonClick_Espresso() {
        onView(withId(R.id.btnToastMessage)).perform(click())
    }

    @Test
    fun validateShouldLaunchHomeScreenViaEspresso() {
        onView(withId(R.id.btnLaunchHome)).perform(click())
        activityScenario?.onActivity { activity ->
            var launchHomeIntent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(launchHomeIntent)
        }
    }

    @Test
    fun validateShouldNotLaunchHomeScreen() {
        activityScenario?.onActivity { activity ->
            var launchHomeIntent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(launchHomeIntent)
        }
    }

}