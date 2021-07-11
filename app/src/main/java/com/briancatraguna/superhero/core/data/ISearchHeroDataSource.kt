package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.domain.SearchHeroResponse

interface ISearchHeroDataSource {
    fun getHeroes(search: String): LiveData<SearchHeroResponse>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>
}