package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.core.di.CoreComponent
import com.briancatraguna.superhero.ui.DetailActivity
import com.briancatraguna.superhero.ui.FavoriteActivity
import com.briancatraguna.superhero.ui.MainActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: FavoriteActivity)
}