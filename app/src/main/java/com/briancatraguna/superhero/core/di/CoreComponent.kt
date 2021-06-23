package com.briancatraguna.superhero.core.di

import android.content.Context
import com.briancatraguna.superhero.core.domain.ISearchHeroRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): ISearchHeroRepository
}