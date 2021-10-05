package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData

interface IHeroRepository {

    fun getHeroes(search: String): LiveData<DomainEntity>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>

    fun getFavoriteHeroes(): LiveData<DomainEntity>

    fun insertFavoriteHero(hero: HeroItem)

    fun deleteFavoriteHero(heroName: String)
}