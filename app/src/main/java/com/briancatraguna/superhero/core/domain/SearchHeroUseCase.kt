package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData

interface SearchHeroUseCase {

    fun getHeroes(search: String): LiveData<SearchHeroResponse>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>

}