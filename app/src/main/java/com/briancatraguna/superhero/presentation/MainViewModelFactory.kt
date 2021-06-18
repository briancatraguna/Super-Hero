package com.briancatraguna.superhero.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.briancatraguna.superhero.di.Injection
import com.briancatraguna.superhero.domain.SearchHeroUseCase
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    private var searchHeroUseCase: SearchHeroUseCase
): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(): MainViewModelFactory =
            instance ?: synchronized(this){
                instance ?: MainViewModelFactory(Injection.provideUseCase())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchHeroesViewModel::class.java) -> SearchHeroesViewModel(searchHeroUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: "+modelClass.name)
        }
    }
}