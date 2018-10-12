package com.jampuero.dogsapp

import android.support.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Joaquín Ampuero on 12-10-18.
 */
class SimpleIdlingResource : IdlingResource {

    @Volatile private var resourceCallback: IdlingResource.ResourceCallback? = null

    private val isIdleNow = AtomicBoolean(true)

    fun setIdleNow(idleNow: Boolean) {
        isIdleNow.set(idleNow)
        if (idleNow) resourceCallback?.onTransitionToIdle()
    }

    override fun getName(): String {
        return "Simple idling resource"
    }

    override fun isIdleNow(): Boolean {
        return isIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }
}