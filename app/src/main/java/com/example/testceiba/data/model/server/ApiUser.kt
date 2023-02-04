package com.example.testceiba.data.model.server

import com.example.testceiba.domain.model.UserView
import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: ApiAddress,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String,
    @SerializedName("company") val company: ApiCompany
) {

    fun mapper(): UserView = UserView(
        userId = id,
        userName = name,
        userPhone = phone,
        userMail = email
        )
}

data class ApiAddress(
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String,
    @SerializedName("geo") val geo: ApiGeo
)

data class ApiGeo(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)

data class ApiCompany(
    @SerializedName("name") val name: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("bs") val bs: String
)


