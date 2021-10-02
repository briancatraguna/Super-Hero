package com.briancatraguna.superhero.core.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.briancatraguna.superhero.core.domain.HeroEntity

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroentity")
    fun getAllHeroes(): LiveData<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(heroEntity: HeroEntity)

    @Query("DELETE FROM heroentity WHERE name = :name")
    fun delete(name: String)
}