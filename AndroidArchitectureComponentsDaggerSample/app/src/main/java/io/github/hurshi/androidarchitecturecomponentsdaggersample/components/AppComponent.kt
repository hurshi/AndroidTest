package io.github.hurshi.androidarchitecturecomponentsdaggersample.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.hurshi.androidarchitecturecomponentsdaggersample.App
import io.github.hurshi.androidarchitecturecomponentsdaggersample.modules.AppModule
import io.github.hurshi.androidarchitecturecomponentsdaggersample.modules.MainActivityModule
import io.github.hurshi.androidarchitecturecomponentsdaggersample.modules.VMModule
import io.github.hurshi.androidarchitecturecomponentsdaggersample.scopes.AppScope

@AppScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        VMModule::class]
)
abstract interface AppComponent : AndroidInjector<App> {

}