package com.briancatraguna.superhero.di

import com.briancatraguna.superhero.core.di.CoreComponent
import com.briancatraguna.superhero.core.presentation.DetailActivity
import com.briancatraguna.superhero.core.presentation.MainActivity
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
}