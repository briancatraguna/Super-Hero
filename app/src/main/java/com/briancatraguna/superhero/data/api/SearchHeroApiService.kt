package com.briancatraguna.superhero.data.api

import com.briancatraguna.superhero.data.SearchHeroResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchHeroApiService {
    @GET("search/{query}")
    fun getHeroes(
        @Path("query") query: String
    ): Call<SearchHeroResponse>
}