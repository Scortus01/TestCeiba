package com.example.testceiba.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testceiba.data.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("Select * From User")
    fun getUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)
}