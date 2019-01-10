package com.example.daggerscope.SubComponent_Demo.components;

import com.example.daggerscope.scopes.ActivityScope;
import com.example.daggerscope.SubComponent_Demo.modules.SBTestModule;
import dagger.Component;

@ActivityScope
@Component(modules = {SBTestModule.class})
public interface SBComponent {
    SBSubComponent.Builder sbSubComponentBuilder();

}
