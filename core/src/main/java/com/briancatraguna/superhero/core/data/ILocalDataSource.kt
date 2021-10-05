package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroEntity

interface ILocalDataSource {
    fun getHeroes(): LiveData<List<HeroEntity>>

    fun insertHero(heroEntity: HeroEntity)

    fun deleteHero(heroName: String)
}