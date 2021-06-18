package com.briancatraguna.superhero.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.briancatraguna.superhero.data.SearchHeroResponse
import com.briancatraguna.superhero.domain.SearchHeroUseCase

class SearchHeroesViewModel(private val searchHeroUseCase: SearchHeroUseCase):ViewModel() {

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