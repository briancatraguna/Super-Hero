package com.briancatraguna.superhero.data.api

import androidx.viewbinding.BuildConfig
import com.briancatraguna.superhero.BuildConfig.HERO_TOKEN
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchHeroApiConfig {
    companion object {
        fun getApiService(): SearchHeroApiService{
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/api.php/10220286508415271/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(SearchHeroApiService::class.java)
        }
    }
}