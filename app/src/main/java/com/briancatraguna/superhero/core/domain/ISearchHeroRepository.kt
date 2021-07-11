package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData

interface ISearchHeroRepository {

    fun getHeroes(search: String): LiveData<SearchHeroResponse>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>
}