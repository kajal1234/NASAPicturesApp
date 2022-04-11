package com.obvious.nasapicturresapp.ui.imagegrid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.obvious.nasapicturresapp.data.model.NasaPictureModel
import com.obvious.nasapicturresapp.data.repository.NasaRepository
import com.obvious.nasapicturresapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: NasaRepository,
    application: Application
) : AndroidViewModel(application) {

    private val TAG: String = this.javaClass.simpleName

    private val _imageListResponse: MutableLiveData<NetworkResult<ArrayList<NasaPictureModel>>> =
        MutableLiveData()
    val imageListResponse: LiveData<NetworkResult<ArrayList<NasaPictureModel>>> = _imageListResponse

    fun nasaPictures() = viewModelScope.launch {
        repository.getNasaPictures().collect { values ->
            _imageListResponse.value = values
        }
    }
}