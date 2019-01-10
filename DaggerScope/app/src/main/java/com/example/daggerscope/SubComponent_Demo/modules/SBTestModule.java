package com.example.daggerscope.SubComponent_Demo.modules;

import android.util.Log;
import com.example.daggerscope.SubComponent_Demo.components.SBSubComponent;
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanA;
import com.example.daggerscope.scopes.ActivityScope;
import dagger.Module;
import dagger.Provides;


@Module(subcomponents = {SBSubComponent.class})
//@Module()
public class SBTestModule {
    @ActivityScope
    @Provides
    SBTestBeanA provideA() {
        Log.e(">>>", "provide SBTestBeanA");
        return new SBTestBeanA("hello");
    }

//    @ActivityScope
//    @Provides
//    SBSubComponent proviceSBSubComponent(SBSubComponent sbSubComponent) {
//        return sbSubComponent;
//    }

// exception: can not found SBTestBeanB
//    @ActivityScope
//    @Provides
//    SBTestBeanDepB provideD(SBTestBeanB sbTestBeanB) {
//        Log.e(">>>", "provide SBTestBeanDepB");
//        return new SBTestBeanDepB(sbTestBeanB);
//    }
}
