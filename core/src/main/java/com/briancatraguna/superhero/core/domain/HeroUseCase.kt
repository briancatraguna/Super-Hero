package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroEntity

interface HeroUseCase {

    fun getHeroes(search: String): LiveData<DomainEntity>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>

    fun getFavoriteHeroes(): LiveData<DomainEntity>

    fun insertFavoriteHero(hero: HeroItem)

    fun deleteFavoriteHero(heroName: String)

}