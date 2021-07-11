package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.briancatraguna.superhero.core.data.api.SearchHeroApiService
import com.briancatraguna.superhero.core.domain.SearchHeroResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchHeroDataSource:ISearchHeroDataSource {

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
        val isLoading: LiveData<Boolean> = _isLoading
        return isLoading
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        val isConnected: LiveData<Boolean> = _isConnected
        return isConnected
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
                _isConnected.postValue(true)
                _isLoading.postValue(true)
                println("onsubscribe")
            }

            override fun onNext(t: SearchHeroResponse) {
                _heroes.postValue(t)
                _isLoading.postValue(false)
                println("onnext")
            }

            override fun onError(e: Throwable) {
                _isConnected.postValue(false)
                _isLoading.postValue(false)
            }

            override fun onComplete() {
                _isLoading.postValue(false)
                println("oncomplete")
            }

        }
    }
}