package com.vjsarathi.colantask.presentation.fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vjsarathi.colantask.data.model.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var rickData: Result? = null

    fun setData(data: Result?) {
        rickData = data
    }

    fun getRickData(): Result? {
        return rickData
    }


}