package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.domain.SearchHeroRepository
import dagger.Component

@Component(modules = [SearchHeroDataSourceModule::class])
interface SearchHeroDataSourceComponent {
    fun inject(searchHeroRepository: SearchHeroRepository)
}