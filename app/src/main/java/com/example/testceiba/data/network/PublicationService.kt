package com.example.testceiba.data.network

import com.example.testceiba.data.model.server.ApiPublication
import retrofit2.http.GET
import retrofit2.http.Query

interface PublicationService {

    @GET("posts")
    suspend fun getListPublication(@Query("userId") userId: Int): List<ApiPublication>

}