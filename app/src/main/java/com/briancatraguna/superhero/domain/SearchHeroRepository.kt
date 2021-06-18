package com.briancatraguna.superhero.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.data.ISearchHeroDataSource
import com.briancatraguna.superhero.data.SearchHeroResponse

class SearchHeroRepository(private val searchHeroDataSource: ISearchHeroDataSource):ISearchHeroRepository {

    companion object{
        @Volatile
        private var instance: SearchHeroRepository? = null

        fun getInstance(searchHeroDataSource: ISearchHeroDataSource): SearchHeroRepository =
            instance ?: synchronized(this){
                instance ?: SearchHeroRepository(searchHeroDataSource).apply {
                    instance = this
                }
            }
    }

    override fun getHeroes(search: String): LiveData<SearchHeroResponse> {
        return searchHeroDataSource.getHeroes(search)
    }

    override fun getConnectionStatus(): LiveData<Boolean> {
        return searchHeroDataSource.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        return searchHeroDataSource.getLoadingStatus()
    }
}