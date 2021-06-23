package com.briancatraguna.superhero.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.briancatraguna.superhero.core.data.SearchHeroResponse
import com.briancatraguna.superhero.core.domain.SearchHeroUseCase

class MainViewModel(private val searchHeroUseCase: SearchHeroUseCase): ViewModel() {

    fun getHeroes(search: String): LiveData<SearchHeroResponse>{
        return searchHeroUseCase.getHeroes(search)
    }

    fun getConnectionStatus(): LiveData<Boolean>{
        return searchHeroUseCase.getConnectionStatus()
    }

    fun getLoadingStatus(): LiveData<Boolean>{
        return searchHeroUseCase.getLoadingStatus()
    }
}