package com.example.smaexample.Helpers;

import com.example.smaexample.Models.TestEntity;
import com.example.smaexample.Models.UserModel;

public class StorageHelper {
    private static StorageHelper instance;
    private UserModel userModel;
    private TestEntity testEntity = new TestEntity();

    public static StorageHelper getInstance()
    {
        if (instance == null)
        {
            instance = new StorageHelper();
        }
        return instance;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }
}
