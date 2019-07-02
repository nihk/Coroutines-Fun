package ca.cbc.testingfun2.util

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource : IdlingResource {

    private val countingIdlingResource = CountingIdlingResource("github_jobs")

    override fun getName(): String = countingIdlingResource.name

    override fun isIdleNow() = countingIdlingResource.isIdleNow

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        countingIdlingResource.registerIdleTransitionCallback(callback)
    }

    fun increment() = countingIdlingResource.increment()

    fun decrement() {
        countingIdlingResource.decrement()
    }
}