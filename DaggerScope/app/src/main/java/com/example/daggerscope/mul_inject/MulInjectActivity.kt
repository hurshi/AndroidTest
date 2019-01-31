package com.example.daggerscope.mul_inject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.daggerscope.mul_inject.components.DaggerMulInjectComponent
import com.example.daggerscope.mul_inject.entity.MulTestBean
import javax.inject.Inject


class MulInjectActivity : AppCompatActivity() {

    @Inject
    lateinit var mulTestBean: MulTestBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMulInjectComponent.create().inject(this)

        Log.e(">>>", "bean = ${mulTestBean.toString()}")

    }
}
