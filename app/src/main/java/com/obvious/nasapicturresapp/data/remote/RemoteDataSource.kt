package com.obvious.nasapicturresapp.data.remote

import com.obvious.nasapicturresapp.data.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiService) {

  suspend fun getNasaData() = service.getPictureDetails()

}