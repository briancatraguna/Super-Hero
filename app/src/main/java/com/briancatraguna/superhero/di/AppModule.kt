package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.core.domain.SearchHeroInteractor
import com.briancatraguna.superhero.core.domain.SearchHeroUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideSearchHeroUseCase(searchHeroInteractor: SearchHeroInteractor): SearchHeroUseCase

}