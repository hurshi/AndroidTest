package com.example.daggerscope.SubComponent_Demo.components;

import com.example.daggerscope.scopes.SubActivityScope;
import com.example.daggerscope.SubComponent_Demo.SubComponentTestActivity;
import com.example.daggerscope.SubComponent_Demo.modules.SBSubTestModule;
import dagger.Subcomponent;

@SubActivityScope
@Subcomponent(modules = {SBSubTestModule.class})
public interface SBSubComponent {

    void inject(SubComponentTestActivity activity);

    void inject(SubComponentTestActivity.ThreadTest threadTest);

    @Subcomponent.Builder
    interface Builder {
        Builder requestModule(SBSubTestModule module);

        SBSubComponent build();
    }
}
