package com.example.test_message.pro.data.network.registrationEntity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfo(

    @SerializedName("phone")
    @Expose
    val phone: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("username")
    @Expose
    val username: String
)
