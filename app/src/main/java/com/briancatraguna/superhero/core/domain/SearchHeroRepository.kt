package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.RemoteDataSource
import javax.inject.Inject

class SearchHeroRepository: ISearchHeroRepository {

    lateinit var remoteDataSource: RemoteDataSource
    lateinit var

    @Inject
    constructor(dataSource: RemoteDataSource){
        this.remoteDataSource = dataSource
    }

    override fun getHeroes(search: String): LiveData<SearchHeroResponse> {
        return remoteDataSource.getHeroes(search)
    }

    override fun getConnectionStatus(): LiveData<Boolean>{
        return remoteDataSource.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean>{
        return remoteDataSource.getLoadingStatus()
    }
}