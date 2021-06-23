package com.briancatraguna.superhero.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.briancatraguna.superhero.core.domain.SearchHeroUseCase
import com.briancatraguna.superhero.di.AppScope
import javax.inject.Inject

@AppScope
class ViewModelFactory: ViewModelProvider.NewInstanceFactory {

    lateinit var searchHeroUseCase: SearchHeroUseCase

    @Inject
    constructor(searchHeroUseCase: SearchHeroUseCase){
        this.searchHeroUseCase = searchHeroUseCase
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(searchHeroUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}