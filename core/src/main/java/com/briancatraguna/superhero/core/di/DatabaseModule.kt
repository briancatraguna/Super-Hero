package com.briancatraguna.superhero.core.di

import android.content.Context
import androidx.room.Room
import com.briancatraguna.superhero.core.data.room.HeroDao
import com.briancatraguna.superhero.core.data.room.HeroDatabase
import net.sqlcipher.database.SQLiteDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SupportFactory

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): HeroDatabase{
        val passphrase: ByteArray = SQLiteDatabase.getBytes("superhero".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "heroes.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideHeroDao(database: HeroDatabase): HeroDao = database.heroDao()
}