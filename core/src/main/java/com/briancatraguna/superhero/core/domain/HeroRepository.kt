package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.LocalDataSource
import com.briancatraguna.superhero.core.data.RemoteDataSource
import com.briancatraguna.superhero.core.data.room.HeroEntity
import javax.inject.Inject

class HeroRepository: IHeroRepository {

    lateinit var remoteDataSource: RemoteDataSource
    lateinit var localDataSource: LocalDataSource

    @Inject
    constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource){
        this.remoteDataSource = remoteDataSource
        this.localDataSource = localDataSource

    }

    override fun getHeroes(search: String): LiveData<DomainEntity> {
        return remoteDataSource.getHeroes(search)
    }

    override fun getConnectionStatus(): LiveData<Boolean>{
        return remoteDataSource.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean>{
        return remoteDataSource.getLoadingStatus()
    }

    override fun getFavoriteHeroes(): LiveData<DomainEntity> {
        return localDataSource.getHeroes()
    }

    override fun insertFavoriteHero(hero: HeroItem) {
        localDataSource.insertHero(hero)
    }

    override fun deleteFavoriteHero(heroName: String) {
        localDataSource.deleteHero(heroName)
    }


}