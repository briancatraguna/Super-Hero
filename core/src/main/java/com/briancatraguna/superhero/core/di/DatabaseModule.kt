package com.briancatraguna.superhero.core.di

import android.content.Context
import androidx.room.Room
import com.briancatraguna.superhero.core.data.room.HeroDao
import com.briancatraguna.superhero.core.data.room.HeroDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): HeroDatabase = Room.databaseBuilder(
        context,
        HeroDatabase::class.java, "heroes.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideHeroDao(database: HeroDatabase): HeroDao = database.heroDao()
}