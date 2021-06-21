package com.briancatraguna.superhero.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.data.ISearchHeroDataSource
import com.briancatraguna.superhero.data.SearchHeroResponse
import com.briancatraguna.superhero.di.DaggerSearchHeroDataSourceComponent
import javax.inject.Inject

class SearchHeroRepository:ISearchHeroRepository {

    @Inject
    lateinit var searchHeroDataSource: ISearchHeroDataSource

    constructor(){
        val dataSourceComponent = DaggerSearchHeroDataSourceComponent.create()
        dataSourceComponent.inject(this)
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