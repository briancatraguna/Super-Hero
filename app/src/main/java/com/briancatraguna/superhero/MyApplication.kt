package com.briancatraguna.superhero

import android.app.Application
import com.briancatraguna.superhero.core.di.CoreComponent
import com.briancatraguna.superhero.core.di.DaggerCoreComponent
import com.briancatraguna.superhero.di.AppComponent
import com.briancatraguna.superhero.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy{
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}