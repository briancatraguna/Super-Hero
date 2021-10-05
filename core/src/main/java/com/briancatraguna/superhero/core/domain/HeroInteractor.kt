package com.briancatraguna.superhero.core.domain

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroEntity
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

    override fun getFavoriteHeroes(): LiveData<List<HeroEntity>> {
        return heroRepository.getFavoriteHeroes()
    }

    override fun insertFavoriteHero(heroEntity: HeroEntity) {
        heroRepository.insertFavoriteHero(heroEntity)
    }

    override fun deleteFavoriteHero(heroName: String) {
        heroRepository.deleteFavoriteHero(heroName)
    }
}