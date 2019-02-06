package io.github.hurshi.androidarchitecturecomponentsdaggersample.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.hurshi.androidarchitecturecomponentsdaggersample.MainActivity
import io.github.hurshi.androidarchitecturecomponentsdaggersample.entity.BindsTestChild
import io.github.hurshi.androidarchitecturecomponentsdaggersample.entity.BindsTestParent
import io.github.hurshi.androidarchitecturecomponentsdaggersample.scopes.ActivityScope

@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


    @Binds
    abstract fun provideBindsTest(b: BindsTestChild): BindsTestParent

}