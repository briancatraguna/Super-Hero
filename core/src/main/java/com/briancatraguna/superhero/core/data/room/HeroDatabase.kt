package com.briancatraguna.superhero.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeroEntity::class], version = 4, exportSchema = false)
abstract class HeroDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
}