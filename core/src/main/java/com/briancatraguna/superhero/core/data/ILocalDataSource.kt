package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.data.room.HeroEntity
import com.briancatraguna.superhero.core.domain.DomainEntity
import com.briancatraguna.superhero.core.domain.HeroItem

interface ILocalDataSource {
    fun getHeroes(): LiveData<DomainEntity>

    fun insertHero(hero: HeroItem)

    fun deleteHero(heroName: String)
}