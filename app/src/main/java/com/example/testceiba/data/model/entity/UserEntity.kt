package com.example.testceiba.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testceiba.domain.model.UserView

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
) {

    fun mapper(): UserView = UserView(
        userId = id,
        userName = name,
        userMail = email,
        userPhone = phone
    )
}