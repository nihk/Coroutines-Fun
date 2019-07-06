package ca.cbc.testingfun2.util

import androidx.test.espresso.IdlingResource

object EspressoIdlingResource : IdlingResource, Countable {
    override fun getName(): String = ""

    override fun isIdleNow(): Boolean = false

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
    }

    override fun increment() {
    }

    override fun decrement() {
    }
}