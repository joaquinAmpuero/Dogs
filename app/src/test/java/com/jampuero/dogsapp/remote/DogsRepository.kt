package com.jampuero.dogsapp.remote

import com.jampuero.dogsapp.models.DogApiResponseModel

/**
 * Created by Joaquín Ampuero on 12-10-18.
 */
class DogsRepository {


    private var terrier = HashMap<String, List<String>>()
    private val dogs: DogApiResponseModel<HashMap<String, List<String>>> = DogApiResponseModel()

    fun getDogs(): DogApiResponseModel<HashMap<String, List<String>>> {
        dogs.status = "success"
        terrier["terrier"] = listOf("american", "australian", "Border")
        dogs.message = terrier
        return dogs
    }
}

