package com.briancatraguna.superhero.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.data.SearchHeroResponse

interface ISearchHeroRepository {
    fun getHeroes(search: String): LiveData<SearchHeroResponse>
    fun getConnectionStatus(): LiveData<Boolean>
    fun getLoadingStatus(): LiveData<Boolean>
}