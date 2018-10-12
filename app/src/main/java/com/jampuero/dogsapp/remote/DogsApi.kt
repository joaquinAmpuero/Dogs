package com.jampuero.dogsapp.remote

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
class DogsApi {
    companion object Singleton {
        const val BASE_URL="https://dog.ceo/api/"
        val INSTANCE: DogsApi by lazy { DogsApi() }
    }

    val paymentApiInstance: DogsApiEndpoints
    init {
        val client = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).serializeNulls().create()
        val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
        val restAdapter = builder.build()
        paymentApiInstance = restAdapter.create(DogsApiEndpoints::class.java)
    }
}