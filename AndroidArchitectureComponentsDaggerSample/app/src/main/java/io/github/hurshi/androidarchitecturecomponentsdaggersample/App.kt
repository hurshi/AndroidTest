package io.github.hurshi.androidarchitecturecomponentsdaggersample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.hurshi.androidarchitecturecomponentsdaggersample.components.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().build()
//        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}