package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData

interface IHeroRepository {

    fun getHeroes(search: String): LiveData<SearchHeroResponse>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>

    fun getFavoriteHeroes(): LiveData<List<HeroEntity>>

    fun insertFavoriteHero(heroEntity: HeroEntity)

    fun deleteFavoriteHero(heroName: String)
}