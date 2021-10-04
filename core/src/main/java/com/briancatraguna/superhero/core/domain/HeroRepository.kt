package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.LocalDataSource
import com.briancatraguna.superhero.core.data.RemoteDataSource
import javax.inject.Inject

class HeroRepository: IHeroRepository {

    lateinit var remoteDataSource: RemoteDataSource
    lateinit var localDataSource: LocalDataSource

    @Inject
    constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource){
        this.remoteDataSource = remoteDataSource
        this.localDataSource = localDataSource

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

    override fun getFavoriteHeroes(): LiveData<List<HeroEntity>> {
        return localDataSource.getHeroes()
    }

    override fun insertFavoriteHero(heroEntity: HeroEntity) {
        localDataSource.insertHero(heroEntity)
    }

    override fun deleteFavoriteHero(heroName: String) {
        localDataSource.deleteHero(heroName)
    }


}