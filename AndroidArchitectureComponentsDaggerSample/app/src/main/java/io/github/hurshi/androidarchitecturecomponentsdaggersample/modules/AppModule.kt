package io.github.hurshi.androidarchitecturecomponentsdaggersample.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.androidarchitecturecomponentsdaggersample.entity.DaoSessionSimulate
import io.github.hurshi.androidarchitecturecomponentsdaggersample.scopes.AppScope

@Module
class AppModule {
    @AppScope
    @Provides
    internal fun providerDao(): DaoSessionSimulate {
        return DaoSessionSimulate("Hello, I am a DB")
    }
}