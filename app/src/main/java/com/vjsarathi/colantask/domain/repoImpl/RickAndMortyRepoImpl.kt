package com.vjsarathi.colantask.domain.repoImpl

import com.vjsarathi.colantask.data.NetworkResult
import com.vjsarathi.colantask.data.model.response.RickAndMortyResponseModel
import com.vjsarathi.colantask.data.repo.RickAndMortyRepo
import com.vjsarathi.colantask.data.service.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RickAndMortyRepoImpl @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) : RickAndMortyRepo {
    override suspend fun getAllRickAndMorty() = flow<NetworkResult<RickAndMortyResponseModel>> {
        val response = rickAndMortyService.getAllRickAndMorty()
        if (response.isSuccessful) {
            emit(NetworkResult.Success(response.body() as RickAndMortyResponseModel))
        } else {
            emit(NetworkResult.Failure("Something went wrong"))
        }
    }.catch { ex ->
        emit(NetworkResult.Failure(ex.message ?: "Unknown Error"))
    }.onStart {
        emit(NetworkResult.Loading(isLoading = true))
    }.onCompletion {
        emit(NetworkResult.Loading(isLoading = false))
    }
}