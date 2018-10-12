package com.jampuero.dogsapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import com.jampuero.dogsapp.SimpleIdlingResource
import com.jampuero.dogsapp.viewmodel.DogsListViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Joaquín Ampuero on 12-10-18.
 */
@RunWith(AndroidJUnit4::class)
class DogListTest {

    private val idlingRes = SimpleIdlingResource()

    @get:Rule
    var activityTestRule: ActivityTestRule<DogsListView> = ActivityTestRule(DogsListView::class.java)

    @Before
    fun idlingResourceSetup() {
        Espresso.registerIdlingResources(idlingRes)
        idlingRes.isIdleNow = false

        val viewModel = ViewModelProviders.of(activityTestRule.activity).get(DogsListViewModel::class.java)


    }

    @Test
    @Throws(Exception::class)
    fun useAppContext() {

        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("com.jampuero.dogsapp", appContext.packageName)
    }

}