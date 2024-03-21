package com.vjsarathi.colantask.presentation.adapter.binder

import androidx.annotation.MainThread
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.vjsarathi.colantask.data.model.response.Result
import com.vjsarathi.colantask.presentation.listener.GenericClickListener

class RickBinder(
    private val listener: GenericClickListener<Result>
) {

    private var rickAndMortyData = MutableLiveData<Result>()
    private var position: Int? = null

    @MainThread
    fun bindData(messageData: Result, position: Int) {
        this.rickAndMortyData.value = messageData
        this.position = position
    }

    val imageUrl = MediatorLiveData<String>().apply {
        addSource(rickAndMortyData) {
            value = it.image
        }
    }

    val name = MediatorLiveData<String>().apply {
        addSource(rickAndMortyData) {
            value = it.name
        }
    }

    fun onClick() {
        position.let {
            listener.onClick(rickAndMortyData.value, it)
        }
    }

}