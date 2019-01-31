package io.github.hurshi.androidarchitecturecomponentsdaggersample.vm_factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.hurshi.androidarchitecturecomponentsdaggersample.scopes.AppScope
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class VMFactory @Inject constructor(private val creator: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator[modelClass]?.get() as T
    }
}