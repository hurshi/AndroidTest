package io.github.hurshi.androidarchitecturecomponentsdaggersample.entity;

import javax.inject.Inject;

public class BindsTestChild implements BindsTestParent {
    @Inject
    public BindsTestChild() {

    }

    @Override
    public String sayHello() {
        return "hello from child";
    }

    public String sayHelloAgain() {
        return "hello again from child";
    }
}
