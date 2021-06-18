package com.briancatraguna.superhero.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.data.SearchHeroResponse

class SearchHeroInteractor(private val searchHeroRepository: ISearchHeroRepository): SearchHeroUseCase {

    companion object{
        @Volatile
        private var instance: SearchHeroInteractor? = null

        fun getInstance(searchHeroRepository: ISearchHeroRepository): SearchHeroInteractor =
            instance ?: synchronized(this){
                instance ?: SearchHeroInteractor(searchHeroRepository).apply {
                    instance = this
                }
            }
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