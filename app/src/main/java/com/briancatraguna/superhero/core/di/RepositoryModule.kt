package com.briancatraguna.superhero.core.di

import com.briancatraguna.superhero.core.domain.ISearchHeroRepository
import com.briancatraguna.superhero.core.domain.SearchHeroRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: SearchHeroRepository): ISearchHeroRepository
}