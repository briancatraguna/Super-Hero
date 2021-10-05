package com.briancatraguna.superhero.core.data.room

import androidx.room.*
import io.reactivex.Observable

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroentity")
    fun getAllHeroes(): Observable<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(heroEntity: HeroEntity)

    @Query("DELETE FROM heroentity WHERE name = :name")
    fun delete(name: String)
}