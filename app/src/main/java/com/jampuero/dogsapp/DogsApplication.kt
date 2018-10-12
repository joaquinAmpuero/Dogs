package com.jampuero.dogsapp

import android.app.Application
import android.content.Context
import com.jampuero.dogsapp.remote.DogsApi

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: DogsApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}