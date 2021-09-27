package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.briancatraguna.superhero.core.data.api.SearchHeroApiService
import com.briancatraguna.superhero.core.domain.SearchHeroResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteDataSource:IRemoteDataSource {

    private val _heroes = MutableLiveData<SearchHeroResponse>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isConnected = MutableLiveData<Boolean>()

    lateinit var apiService: SearchHeroApiService

    @Inject
    constructor(apiService: SearchHeroApiService){
        this.apiService = apiService
    }

    override fun getHeroes(search: String): LiveData<SearchHeroResponse> {
        runQuery(search)
        val heroes: LiveData<SearchHeroResponse> = _heroes
        return heroes
    }

    override fun getConnectionStatus(): LiveData<Boolean> {
        val isConnected: LiveData<Boolean> = _isConnected
        return isConnected
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        val isLoading: LiveData<Boolean> = _isLoading
        return isLoading
    }

    private fun runQuery(search: String){
        apiService.getHeroes(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getHeroesListObserverRx())
    }

    private fun getHeroesListObserverRx(): Observer<SearchHeroResponse>{
        return object : Observer<SearchHeroResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(t: SearchHeroResponse) {
                if (t.response != "error"){
                    _heroes.value = t
                } else {
                    _heroes.value = SearchHeroResponse(
                        resultsFor = null,
                        response = "error",
                        results = null
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }

        }
    }
}