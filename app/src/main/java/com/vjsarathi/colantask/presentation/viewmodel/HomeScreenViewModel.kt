package com.vjsarathi.colantask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vjsarathi.colantask.data.NetworkResult
import com.vjsarathi.colantask.data.model.response.RickAndMortyResponseModel
import com.vjsarathi.colantask.data.repo.RickAndMortyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val rickAndMortyRepo: RickAndMortyRepo
) : ViewModel() {

    private val TAG = this@HomeScreenViewModel.javaClass.simpleName

    private val _rickAndMortyResponse = MutableLiveData<RickAndMortyResponseModel>()
    val rickAndMortyResponse: LiveData<RickAndMortyResponseModel> get() = _rickAndMortyResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> get() = _isError


    init {
        getAllRickAndMorty()
    }

    private fun getAllRickAndMorty() {
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyRepo.getAllRickAndMorty().collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is NetworkResult.Failure -> {
                            Log.e(TAG, result.errorMessage)
                            _isError.value = result.errorMessage
                        }

                        is NetworkResult.Loading -> {
                            _isLoading.value = result.isLoading
                        }

                        is NetworkResult.Success -> {
                            Log.i(TAG, "${result.data}")
                            result.data.let {
                                _rickAndMortyResponse.value = it
                            }
                        }
                    }
                }
            }
        }
    }

}