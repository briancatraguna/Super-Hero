package com.briancatraguna.superhero.core.di

import com.briancatraguna.superhero.core.data.IRemoteDataSource
import com.briancatraguna.superhero.core.data.RemoteDataSource
import com.briancatraguna.superhero.core.data.api.SearchHeroApiService
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesApiService(): SearchHeroApiService {
        val baseUrl = "https://www.superheroapi.com/api.php/10220286508415271/"
        val hostName = "superheroapi.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName,"sha256/M1qLUHrCEGtTwnkjXC5X7Igl0VQD5GRJx/K8S+MMU9s=")
            .add(hostName,"sha256/7pLvtcruOarW7FiLCfZU4tL9+/DRgQn144J5w4zYVaY=")
            .build()
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .certificatePinner(certificatePinner)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        return retrofit.create(SearchHeroApiService::class.java)
    }

    @Provides
    fun providesDataSource(apiService: SearchHeroApiService): IRemoteDataSource {
        return RemoteDataSource(apiService)
    }
}