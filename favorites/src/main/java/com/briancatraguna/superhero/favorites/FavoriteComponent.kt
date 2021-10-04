package com.briancatraguna.superhero.favorites

import com.briancatraguna.superhero.core.di.CoreComponent
import com.briancatraguna.superhero.di.AppModule
import com.briancatraguna.superhero.di.AppScope
import com.briancatraguna.superhero.di.FavoriteScope
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface FavoriteComponent{
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }
    fun inject(activity: FavoriteActivity)
}