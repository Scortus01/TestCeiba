package com.example.testceiba.data.network

import com.example.testceiba.data.model.server.ApiUser
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getListUsers(): List<ApiUser>
}