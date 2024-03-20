package com.vjsarathi.colantask.data.repo

import com.vjsarathi.colantask.data.NetworkResult
import com.vjsarathi.colantask.data.model.response.RickAndMortyResponseModel
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepo {

    suspend fun getAllRickAndMorty(): Flow<NetworkResult<RickAndMortyResponseModel>>

}