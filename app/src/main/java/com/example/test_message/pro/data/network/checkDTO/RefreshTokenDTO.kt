package com.example.test_message.pro.data.network.checkDTO

import com.example.test_message.pro.data.network.authDTO.DetailArrayDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RefreshTokenDTO(
    @SerializedName("refresh_token")
    @Expose
    val refreshToken: String,
    @SerializedName("access_token")
    @Expose
    val accessToken: String,

    @SerializedName("user_id")
    @Expose
    val userID: Int,

    @SerializedName("message")
    @Expose
    val message: String,

    @SerializedName("detail")
    @Expose
    val error: DetailArrayDTO?
    )