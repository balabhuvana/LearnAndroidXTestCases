package com.example.learnandroidxtestcases

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import com.example.learnandroidxtestcases.activity.ContactsActivity
import com.example.learnandroidxtestcases.activity.DialerActivity
import com.example.learnandroidxtestcases.activity.DialerActivity.Companion.VALID_PHONE_NUMBER
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DialerTestActivityTest {

    @Rule
    @JvmField
    var grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant("android.permission.CALL_PHONE")

    @Rule
    @JvmField
    var mActivityRule: IntentsTestRule<DialerActivity> = IntentsTestRule<DialerActivity>(
        DialerActivity::class.java
    )

    @Before
    fun stubAllExternalIntents() {
        Intents.intending(Matchers.not(IntentMatchers.isInternal()))
            .respondWith(
                ActivityResult(
                    Activity.RESULT_OK, null
                )
            )
    }

    @Test
    fun validatePickContact() {
        onView(withId(R.id.edit_text_caller_number))
            .perform(
                typeText(VALID_PHONE_NUMBER),
                closeSoftKeyboard()
            )
        onView(withId(R.id.button_call_number)).perform(click())
        Intents.intended(
            AllOf.allOf(
                IntentMatchers.hasAction(Intent.ACTION_CALL),
                IntentMatchers.hasData(INTENT_DATA_PHONE_NUMBER)
            )
        )
    }

    @Test
    fun typeNumber_ValidInput_InitiatesCall_truth() {
        onView(withId(R.id.edit_text_caller_number))
            .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard())
        onView(withId(R.id.button_call_number)).perform(click())

        val receivedIntent: Intent =
            Iterables.getOnlyElement(Intents.getIntents())

        assertThat(receivedIntent).hasAction(Intent.ACTION_CALL)
        assertThat(receivedIntent).hasData(INTENT_DATA_PHONE_NUMBER)

    }

    @Test
    fun pickContactButton_click_SelectsPhoneNumber() {

        Intents.intending(
            hasComponent(
                hasShortClassName(".ContactsActivity")
            )
        )
            .respondWith(
                ActivityResult(
                    Activity.RESULT_OK,
                    ContactsActivity.createResultData(VALID_PHONE_NUMBER)
                )
            )
        // Click the pick contact button.
        onView(withId(R.id.button_pick_contact)).perform(click())
        // Check that the number is displayed in the UI.
        onView(withId(R.id.edit_text_caller_number))
            .check(matches(withText(VALID_PHONE_NUMBER)))
    }

    companion object {

        private val INTENT_DATA_PHONE_NUMBER =
            Uri.parse("tel:$VALID_PHONE_NUMBER")
    }
}