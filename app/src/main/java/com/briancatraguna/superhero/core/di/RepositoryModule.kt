package com.briancatraguna.superhero.core.di

import com.briancatraguna.superhero.core.domain.IHeroRepository
import com.briancatraguna.superhero.core.domain.HeroRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: HeroRepository): IHeroRepository
}