package com.example.learnandroidxtestcases

import android.Manifest
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.example.learnandroidxtestcases.ImageViewHasDrawableMatcher.hasDrawable
import com.example.learnandroidxtestcases.activity.CameraScreenActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CameraScreenActivityTest {

    @Rule
    @JvmField
    var grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.CAMERA)

    @Rule
    @JvmField
    var cameraIntentsTestRule =
        IntentsTestRule<CameraScreenActivity>(CameraScreenActivity::class.java)

    @Before
    fun setUp() {
        var activityResult = createImageCaptureActivityResultStub()
        Intents.intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult)
    }

    @Test
    fun validateLaunchCamera() {
        // Check that the ImageView doesn't have a drawable applied.
        onView(withId(R.id.ivCaptureImage)).check(matches(not(hasDrawable())))
        onView(withId(R.id.btnStartCamera)).perform(click())

        // With no user interaction, the ImageView will have a drawable.
        onView(withId(R.id.ivCaptureImage)).check(matches(hasDrawable()))
    }

    fun createImageCaptureActivityResultStub(): ActivityResult {
        val bundle = Bundle()
        // Image should be in .png format not in xml format. if we use xml it will be null
        bundle.putParcelable(
            CameraScreenActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                cameraIntentsTestRule.getActivity().getResources(),
                R.drawable.ic_launcher
            )
        )

        val resultData = Intent()
        resultData.putExtras(bundle)

        return ActivityResult(Activity.RESULT_OK, resultData)
    }
}