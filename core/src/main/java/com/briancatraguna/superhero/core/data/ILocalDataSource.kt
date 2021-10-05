package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroEntity
import com.briancatraguna.superhero.core.domain.DomainEntity

interface ILocalDataSource {
    fun getHeroes(): LiveData<DomainEntity>

    fun insertHero(heroEntity: HeroEntity)

    fun deleteHero(heroName: String)
}