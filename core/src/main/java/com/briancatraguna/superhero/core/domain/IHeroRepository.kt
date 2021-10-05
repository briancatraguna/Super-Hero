package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroEntity

interface IHeroRepository {

    fun getHeroes(search: String): LiveData<DomainEntity>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>

    fun getFavoriteHeroes(): LiveData<List<HeroEntity>>

    fun insertFavoriteHero(heroEntity: HeroEntity)

    fun deleteFavoriteHero(heroName: String)
}