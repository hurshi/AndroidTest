package com.example.daggerscope.SubComponent_Demo.modules;

import android.util.Log;
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanA;
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanDepA;
import com.example.daggerscope.scopes.SubActivityScope;
import com.example.daggerscope.SubComponent_Demo.entity.SBTestBeanB;
import dagger.Module;
import dagger.Provides;

@Module
public class SBSubTestModule {
    @SubActivityScope
    @Provides
    SBTestBeanB provideB() {
        Log.e(">>>", "provide SBTestBeanB");
        return new SBTestBeanB("hello");
    }

    @SubActivityScope
    @Provides
    SBTestBeanDepA provideC(SBTestBeanA sbTestBeanA) {
        Log.e(">>>", "provide SBTestBeanDepA");
        return new SBTestBeanDepA(sbTestBeanA);
    }
}
