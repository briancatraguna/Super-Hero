package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import com.briancatraguna.superhero.core.data.room.HeroDao
import com.briancatraguna.superhero.core.data.room.HeroEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalDataSource: ILocalDataSource {

    private val _heroes = MutableLiveData<List<HeroEntity>>()

    lateinit var heroDao: HeroDao

    @Inject
    constructor(heroDao: HeroDao){
        this.heroDao = heroDao
    }

    override fun getHeroes(): LiveData<List<HeroEntity>> {
        loadData()
        val heroes: LiveData<List<HeroEntity>> = _heroes
        return heroes
    }

    override fun insertHero(heroEntity: HeroEntity) {
        heroDao.insert(heroEntity)
    }

    override fun deleteHero(heroName: String) {
        heroDao.delete(heroName)
    }

    private fun loadData(){
        heroDao.getAllHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getHeroesListObserverRx())
    }

    private fun getHeroesListObserverRx(): Observer<List<HeroEntity>>{
        return object : Observer<List<HeroEntity>>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<HeroEntity>) {
                _heroes.value = t
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        }
    }
}