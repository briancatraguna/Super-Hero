package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.domain.HeroEntity
import com.briancatraguna.superhero.core.domain.SearchHeroResponse

interface ILocalDataSource {
    fun getHeroes(): LiveData<List<HeroEntity>>

    fun insertHero(heroEntity: HeroEntity)
}