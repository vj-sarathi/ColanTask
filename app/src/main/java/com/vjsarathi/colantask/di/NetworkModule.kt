package com.vjsarathi.colantask.di

import com.vjsarathi.colantask.data.repo.RickAndMortyRepo
import com.vjsarathi.colantask.data.service.RickAndMortyService
import com.vjsarathi.colantask.domain.repoImpl.RickAndMortyRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiClient(
        retrofit: Retrofit
    ): RickAndMortyService {
        return retrofit.create(RickAndMortyService::class.java)
    }

    @Provides
    fun provideRickAndMortyRepo(
        rickAndMortyService: RickAndMortyService
    ): RickAndMortyRepo = RickAndMortyRepoImpl(rickAndMortyService)

}