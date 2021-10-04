package com.briancatraguna.superhero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.briancatraguna.superhero.core.domain.HeroEntity
import com.briancatraguna.superhero.core.domain.SearchHeroResponse
import com.briancatraguna.superhero.core.domain.HeroUseCase

class MainViewModel(private val heroUseCase: HeroUseCase): ViewModel() {

    fun getHeroes(search: String): LiveData<SearchHeroResponse>{
        return heroUseCase.getHeroes(search)
    }

    fun getConnectionStatus(): LiveData<Boolean>{
        return heroUseCase.getConnectionStatus()
    }

    fun getLoadingStatus(): LiveData<Boolean>{
        return heroUseCase.getLoadingStatus()
    }

    fun getFavoriteHeroes(): LiveData<List<HeroEntity>>{
        return heroUseCase.getFavoriteHeroes()
    }

    fun insertFavoriteHero(heroEntity: HeroEntity){
        heroUseCase.insertFavoriteHero(heroEntity)
    }

    fun deleteFavoriteHero(heroName: String){
        heroUseCase.deleteFavoriteHero(heroName)
    }
}