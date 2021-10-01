package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.core.domain.HeroInteractor
import com.briancatraguna.superhero.core.domain.HeroUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideSearchHeroUseCase(heroInteractor: HeroInteractor): HeroUseCase

}