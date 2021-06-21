package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.data.ISearchHeroDataSource
import com.briancatraguna.superhero.data.SearchHeroDataSource
import dagger.Module
import dagger.Provides

@Module
class SearchHeroDataSourceModule {

    @Provides
    fun providesDataSource(searchHeroDataSource: SearchHeroDataSource): ISearchHeroDataSource{
        return searchHeroDataSource
    }
}