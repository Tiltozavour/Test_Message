package com.example.test_message.pro.data.network.registrationEntity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailSuccess(

    @SerializedName("refresh_token")
    @Expose
    val refreshToken: String,

    @SerializedName("access_token")
    @Expose
    val accessToken: String,

    @SerializedName("user_id")
    @Expose
    val userId: Int

)