package com.briancatraguna.superhero.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.briancatraguna.superhero.data.api.SearchHeroApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchHeroDataSource: ISearchHeroDataSource {

    private val _heroes = MutableLiveData<SearchHeroResponse>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isConnected = MutableLiveData<Boolean>()

    companion object {
        @Volatile
        private var instance: SearchHeroDataSource? = null

        fun getInstance(): SearchHeroDataSource =
            instance ?: synchronized(this){
                instance ?: SearchHeroDataSource().apply {
                    instance = this
                }
            }
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
        _isConnected.value = true
        _isLoading.value = true
        val client = SearchHeroApiConfig.getApiService().getHeroes(search)
        client.enqueue(object : Callback<SearchHeroResponse>{
            override fun onResponse(
                call: Call<SearchHeroResponse>,
                response: Response<SearchHeroResponse>
            ) {
                if (response.isSuccessful){
                    _heroes.value = response.body()
                    _isLoading.value = false
                } else {
                    _isLoading.value = false
                    _isConnected.value = false
                }

            }

            override fun onFailure(call: Call<SearchHeroResponse>, t: Throwable) {
                _isLoading.value = false
                _isConnected.value = false
            }

        })
    }
}