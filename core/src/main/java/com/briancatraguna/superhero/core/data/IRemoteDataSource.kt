package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import com.briancatraguna.superhero.core.domain.DomainEntity

interface IRemoteDataSource {
    fun getHeroes(search: String): LiveData<DomainEntity>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>
}