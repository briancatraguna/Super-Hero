package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.SearchHeroDataSource
import com.briancatraguna.superhero.core.data.SearchHeroResponse
import javax.inject.Inject

class SearchHeroRepository: ISearchHeroRepository {

    lateinit var searchHeroDataSource: SearchHeroDataSource

    @Inject
    constructor(dataSource: SearchHeroDataSource){
        this.searchHeroDataSource = dataSource
    }

    override fun getHeroes(search: String): LiveData<SearchHeroResponse> {
        return searchHeroDataSource.getHeroes(search)
    }

    override fun getConnectionStatus(): LiveData<Boolean>{
        return searchHeroDataSource.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean>{
        return searchHeroDataSource.getLoadingStatus()
    }
}