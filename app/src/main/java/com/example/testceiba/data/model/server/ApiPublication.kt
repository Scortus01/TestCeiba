package com.example.testceiba.data.model.server

import com.example.testceiba.domain.model.PublicationView
import com.google.gson.annotations.SerializedName

data class ApiPublication(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
){
    fun mapper(): PublicationView = PublicationView(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
