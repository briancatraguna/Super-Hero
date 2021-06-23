package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.SearchHeroResponse
import javax.inject.Inject

class SearchHeroInteractor: SearchHeroUseCase {

    lateinit var searchHeroRepository: ISearchHeroRepository

    @Inject
    constructor(searchHeroRepository: ISearchHeroRepository){
        this.searchHeroRepository = searchHeroRepository
    }

    override fun getHeroes(search: String): LiveData<SearchHeroResponse> {
        return searchHeroRepository.getHeroes(search)
    }

    override fun getConnectionStatus(): LiveData<Boolean> {
        return searchHeroRepository.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        return searchHeroRepository.getLoadingStatus()
    }
}