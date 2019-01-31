package io.github.hurshi.androidarchitecturecomponentsdaggersample.vm

import android.arch.lifecycle.ViewModel
import io.github.hurshi.androidarchitecturecomponentsdaggersample.entity.DaoSessionSimulate
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val daoSessionSimulate: DaoSessionSimulate) : ViewModel() {

    fun getDaoSessionSimulate(): DaoSessionSimulate {
        return daoSessionSimulate
    }

}