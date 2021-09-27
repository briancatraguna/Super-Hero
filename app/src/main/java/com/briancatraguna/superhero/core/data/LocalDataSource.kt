package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroDao
import com.briancatraguna.superhero.core.domain.HeroEntity
import javax.inject.Inject

class LocalDataSource: ILocalDataSource {

    lateinit var heroDao: HeroDao

    @Inject
    constructor(heroDao: HeroDao){
        this.heroDao = heroDao
    }

    override fun getHeroes(): LiveData<List<HeroEntity>> {
        return heroDao.getAllHeroes()
    }

    override fun insertHero(heroEntity: HeroEntity) {
        heroDao.insert(heroEntity)
    }
}