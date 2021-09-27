package com.briancatraguna.superhero.core.di

import com.briancatraguna.superhero.core.data.IRemoteDataSource
import com.briancatraguna.superhero.core.data.RemoteDataSource
import com.briancatraguna.superhero.core.data.api.SearchHeroApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesApiService(): SearchHeroApiService{
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/10220286508415271/")
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