package com.example.daggerscope.entity;

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "hashCode" + hashCode() +
                "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
