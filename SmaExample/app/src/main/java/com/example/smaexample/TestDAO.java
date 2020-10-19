package com.example.smaexample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TestDAO {
    @Query("SELECT * FROM testentity")
    List<TestEntity> getAll();

    @Query("SELECT * FROM testentity WHERE id IN (:userIds)")
    List<TestEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM testentity WHERE name LIKE :first LIMIT 1")
    TestEntity findByName(String first);

    @Insert
    void insertAll(TestEntity... users);

    @Delete
    void delete(TestEntity user);
}
