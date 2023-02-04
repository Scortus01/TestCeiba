package com.example.testceiba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testceiba.data.dao.UserDAO
import com.example.testceiba.data.model.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class TestDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
}
