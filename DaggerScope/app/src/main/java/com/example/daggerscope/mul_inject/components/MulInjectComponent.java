package com.example.daggerscope.mul_inject.components;

import com.example.daggerscope.mul_inject.MulInjectActivity;
import com.example.daggerscope.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component()
public interface MulInjectComponent {
    void inject(MulInjectActivity activity);
}
