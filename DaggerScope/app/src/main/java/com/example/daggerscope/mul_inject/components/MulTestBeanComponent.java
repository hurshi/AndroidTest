package com.example.daggerscope.mul_inject.components;

import com.example.daggerscope.mul_inject.entity.MulTestBean;
import com.example.daggerscope.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component()
public interface MulTestBeanComponent {
    void inject(MulTestBean bean);
}
