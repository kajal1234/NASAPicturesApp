package com.obvious.nasapicturresapp.data.repository

import com.obvious.nasapicturresapp.data.model.BaseApiResponse
import com.obvious.nasapicturresapp.data.model.NasaPictureModel
import com.obvious.nasapicturresapp.data.remote.RemoteDataSource
import com.obvious.nasapicturresapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class NasaRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
  BaseApiResponse() {

  suspend fun getNasaPictures(): Flow<NetworkResult<ArrayList<NasaPictureModel>>> {
    return flow {
      emit(NetworkResult.Loading())
      emit(safeApiCall { remoteDataSource.getNasaData() })
    }.flowOn(Dispatchers.IO)
  }

}