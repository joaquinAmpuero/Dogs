package com.jampuero.dogsapp.remote

import com.jampuero.dogsapp.models.DogApiResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Joaquín Ampuero on 11-10-18.
 */
interface DogsApiEndpoints {

    @GET("breeds/list/all")
    fun getAllDogBreeds(): Observable<DogApiResponseModel<HashMap<String, List<String>>>>

    @GET("breed/{type}/images")
    fun getBreedsImages(@Path("type") platformID: String): Observable<DogApiResponseModel<List<String>>>

}