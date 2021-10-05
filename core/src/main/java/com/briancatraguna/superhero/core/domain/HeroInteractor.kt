package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class HeroInteractor: HeroUseCase {

    lateinit var heroRepository: IHeroRepository

    @Inject
    constructor(heroRepository: IHeroRepository){
        this.heroRepository = heroRepository
    }

    override fun getHeroes(search: String): LiveData<DomainEntity> {
        return heroRepository.getHeroes(search)
    }

    override fun getConnectionStatus(): LiveData<Boolean> {
        return heroRepository.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        return heroRepository.getLoadingStatus()
    }

    override fun getFavoriteHeroes(): LiveData<DomainEntity> {
        return heroRepository.getFavoriteHeroes()
    }

    override fun insertFavoriteHero(hero: HeroItem) {
        heroRepository.insertFavoriteHero(hero)
    }

    override fun deleteFavoriteHero(heroName: String) {
        heroRepository.deleteFavoriteHero(heroName)
    }
}