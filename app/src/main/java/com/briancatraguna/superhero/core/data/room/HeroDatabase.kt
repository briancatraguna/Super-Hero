package com.briancatraguna.superhero.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.briancatraguna.superhero.core.domain.HeroEntity

@Database(entities = [HeroEntity::class], version = 2, exportSchema = false)
abstract class HeroDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
}