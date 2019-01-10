package com.example.daggerscope.SubComponent_Demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.daggerscope.R
import com.example.daggerscope.SubComponent_Demo.components.DaggerSBComponent
import com.example.daggerscope.SubComponent_Demo.components.SBComponent
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanA
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanB
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanDepA
import javax.inject.Inject

class SubComponentTestActivity : AppCompatActivity() {
    @Inject
    lateinit var sbTestBeanA: SBTestBeanA

    @Inject
    lateinit var sbTestBeanB: SBTestBeanB

    @Inject
    lateinit var sbTestBeanDepA: SBTestBeanDepA

//    @Inject
//    lateinit var sbTestBeanDepB: SBTestBeanDepB

    private lateinit var sbComponent: SBComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sbComponent = DaggerSBComponent
            .builder().build()
        sbComponent.sbSubComponentBuilder()
            .build()
            .inject(this)

        setContentView(R.layout.activity_main)
        sbTestBeanA.pt()
        sbTestBeanB.pt()
        sbTestBeanDepA.pt()
//        sbTestBeanDepB.pt()

        ThreadTest(sbComponent).start()
    }

    class ThreadTest(private val sbComponent: SBComponent) : Thread() {
        @Inject
        lateinit var sbTestBeanA: SBTestBeanA

        @Inject
        lateinit var sbTestBeanB: SBTestBeanB

        @Inject
        lateinit var sbTestBeanDepA: SBTestBeanDepA

//        @Inject
//        lateinit var sbTestBeanDepB: SBTestBeanDepB

        override fun run() {
            super.run()
            sbComponent.sbSubComponentBuilder().build().inject(this)
            sbTestBeanA.pt("ThreadTest")
            sbTestBeanB.pt("ThreadTest")
            sbTestBeanDepA.pt("ThreadTest")
//            sbTestBeanDepB.pt("ThreadTest")
        }
    }
}
