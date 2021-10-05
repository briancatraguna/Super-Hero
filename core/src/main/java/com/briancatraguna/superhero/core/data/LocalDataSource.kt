package com.briancatraguna.superhero.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import com.briancatraguna.superhero.core.data.room.HeroDao
import com.briancatraguna.superhero.core.data.room.HeroEntity
import com.briancatraguna.superhero.core.domain.DomainEntity
import com.briancatraguna.superhero.core.domain.HeroItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalDataSource: ILocalDataSource {

    private val _heroes = MutableLiveData<DomainEntity>()

    lateinit var heroDao: HeroDao

    @Inject
    constructor(heroDao: HeroDao){
        this.heroDao = heroDao
    }

    override fun getHeroes(): LiveData<DomainEntity> {
        loadData()
        val heroes: LiveData<DomainEntity> = _heroes
        return heroes
    }

    override fun insertHero(hero: HeroItem) {
        val heroEntity = HeroEntity(
            id = 0,
            image = hero.image.toString(),
            name = hero.name.toString(),
            desc = hero.desc.toString(),
            strength = hero.strength.toString(),
            durability = hero.durability.toString(),
            combat = hero.combat.toString(),
            power = hero.power.toString(),
            speed = hero.speed.toString(),
            intelligence = hero.intelligence.toString()
        )
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
                val resultsList = mutableListOf<HeroItem>()
                for (item in t){
                    resultsList.add(
                        HeroItem(
                            image = item.image,
                            name = item.name,
                            desc = item.desc,
                            strength = item.strength,
                            durability = item.durability,
                            combat = item.combat,
                            power = item.power,
                            speed = item.speed,
                            intelligence = item.intelligence
                        )
                    )
                }
                val domainEntity = DomainEntity(
                    false,
                    resultsList
                )
                _heroes.value = domainEntity
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        }
    }
}