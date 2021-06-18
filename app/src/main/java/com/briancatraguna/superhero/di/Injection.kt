package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.data.ISearchHeroDataSource
import com.briancatraguna.superhero.data.SearchHeroDataSource
import com.briancatraguna.superhero.domain.ISearchHeroRepository
import com.briancatraguna.superhero.domain.SearchHeroInteractor
import com.briancatraguna.superhero.domain.SearchHeroRepository
import com.briancatraguna.superhero.domain.SearchHeroUseCase

object Injection {
    fun provideUseCase(): SearchHeroUseCase {
        val searchHeroRepository = provideRepository()
        return SearchHeroInteractor.getInstance(searchHeroRepository)
    }

    private fun provideRepository(): ISearchHeroRepository {
        val searchHeroDataSource = provideDataSource()
        return SearchHeroRepository.getInstance(searchHeroDataSource)
    }

    private fun provideDataSource(): ISearchHeroDataSource {
        return SearchHeroDataSource.getInstance()

    }
}