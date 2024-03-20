package com.vjsarathi.colantask.data.service

import com.vjsarathi.colantask.data.model.response.RickAndMortyResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("api/character")
    suspend fun getAllRickAndMorty(): Response<RickAndMortyResponseModel>

}