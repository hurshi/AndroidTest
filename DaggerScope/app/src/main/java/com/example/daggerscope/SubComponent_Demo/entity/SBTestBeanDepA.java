package com.example.daggerscope.SubComponent_Demo.entity;

import android.util.Log;

public class SBTestBeanDepA {
    private SBTestBeanA sbTestBeanA;

    public SBTestBeanDepA(SBTestBeanA sbTestBeanA) {
        this.sbTestBeanA = sbTestBeanA;
    }

    public void pt(String... strings) {
        StringBuilder s = new StringBuilder(" ");
        if (null != strings) {
            for (int i = 0; i < strings.length; i++) {
                s.append(strings[i]);
            }
        }
        Log.e(">>>", toString() + s.toString());
    }

    @Override
    public String toString() {
        return "hashCode=" + hashCode() + "," +
                "SBTestBeanDepA{" +
                "sbTestBeanA=" + sbTestBeanA.toString() +
                '}';
    }
}
