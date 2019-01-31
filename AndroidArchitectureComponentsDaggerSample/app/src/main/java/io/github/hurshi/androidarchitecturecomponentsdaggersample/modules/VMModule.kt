package io.github.hurshi.androidarchitecturecomponentsdaggersample.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.android.example.github.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.hurshi.androidarchitecturecomponentsdaggersample.vm.MainActivityViewModel
import io.github.hurshi.androidarchitecturecomponentsdaggersample.vm_factory.VMFactory

@Module
abstract class VMModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityModule(mm: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: VMFactory): ViewModelProvider.Factory
}