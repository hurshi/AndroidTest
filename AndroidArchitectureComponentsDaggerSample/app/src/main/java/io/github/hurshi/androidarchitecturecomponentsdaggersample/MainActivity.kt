package io.github.hurshi.androidarchitecturecomponentsdaggersample

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import io.github.hurshi.androidarchitecturecomponentsdaggersample.entity.BindsTestParent
import io.github.hurshi.androidarchitecturecomponentsdaggersample.entity.DaoSessionSimulate
import io.github.hurshi.androidarchitecturecomponentsdaggersample.vm.MainActivityViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var daoSessionSimulate: DaoSessionSimulate

    @Inject
    lateinit var b: BindsTestParent

    private val vm: MainActivityViewModel by lazy {
        ViewModelProviders.of(this@MainActivity, factory).get(MainActivityViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(">>>", "daoSessionSimulate = $daoSessionSimulate")
        Log.e(">>>", "daoSessionSimulate from vm = ${vm.getDaoSessionSimulate()}")
        Log.e(">>>", "binds = ${b.sayHello()}")
    }
}
