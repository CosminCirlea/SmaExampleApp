package com.example.smaexample.Models;

public class UserModel {
    private String name;
    private String firsname;
    private String email;
    private int age;

    public UserModel(String name, String firsname, String email, int age) {
        this.name = name;
        this.firsname = firsname;
        this.email = email;
        this.age = age;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
