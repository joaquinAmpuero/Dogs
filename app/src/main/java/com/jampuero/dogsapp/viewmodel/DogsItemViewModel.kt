package com.jampuero.dogsapp.viewmodel

import android.arch.lifecycle.ViewModel

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsItemViewModel : ViewModel() {
    fun breedToShow(breed: String):String {
        return breed
    }

}