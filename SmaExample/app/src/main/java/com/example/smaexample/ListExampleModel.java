package com.example.smaexample;

public class ListExampleModel {
    private String name;
    private String firstname;
    private int age;

    public ListExampleModel(String name, String firstname, int age) {
        this.name = name;
        this.firstname = firstname;
        this.age = age;
    }

    public ListExampleModel(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
