package com.jampuero.dogsapp.remote

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Observer
import retrofit2.HttpException

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
abstract class CustomObserver <T> : Observer<T> {

    override fun onNext(t: T) {
        onSuccess(t)
    }

    abstract fun onSuccess(response: T)

}