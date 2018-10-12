package com.jampuero.dogsapp.models

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogApiResponseModel<T> {
    lateinit var status: String
    var message: T? = null
}