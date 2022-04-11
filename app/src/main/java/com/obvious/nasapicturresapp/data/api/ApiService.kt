package com.obvious.nasapicturresapp.data.api

import com.obvious.nasapicturresapp.data.model.NasaPictureModel
import retrofit2.Response
import retrofit2.http.*

/**
 * Developed by Kajal Kukdeja on 10,April,2022
 * Api services to communicate with server
 */

interface ApiService {

    /**
     * Fetch Nasa pictures from url
     */

    @GET("uc?export=download&id=18t-LzVG7bxu-oPxJQZg8P49I9UHcA552")
    suspend fun getPictureDetails(
    ): Response<ArrayList<NasaPictureModel>>
}


