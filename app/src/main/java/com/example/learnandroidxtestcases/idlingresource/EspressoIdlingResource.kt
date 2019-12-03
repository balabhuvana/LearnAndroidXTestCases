package com.example.learnandroidxtestcases.idlingresource

import androidx.test.espresso.IdlingResource


object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val mCountingIdlingResource: SimpleIdlingResource =
        SimpleIdlingResource(
            RESOURCE
        )

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource
}