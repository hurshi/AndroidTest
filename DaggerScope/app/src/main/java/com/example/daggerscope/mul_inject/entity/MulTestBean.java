package com.example.daggerscope.mul_inject.entity;

import com.example.daggerscope.mul_inject.components.DaggerMulTestBeanComponent;

import javax.inject.Inject;

public class MulTestBean {
    @Inject
    MulTestBean2 bean2;

    @Inject
    public MulTestBean() {
        DaggerMulTestBeanComponent.create().inject(this);
    }

    @Override
    public String toString() {
        return "hello MulTestBean bean2 = " + bean2.toString();
    }
}
