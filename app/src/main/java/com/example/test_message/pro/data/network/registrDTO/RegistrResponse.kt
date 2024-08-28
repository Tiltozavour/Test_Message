package com.example.test_message.pro.data.network.registrDTO

import com.example.test_message.pro.data.network.authDTO.DetailDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegistrResponse(

    @SerializedName("refresh_token")
    @Expose
    val refreshToken: String,

    @SerializedName("access_token")
    @Expose
    val accessToken: String,

    @SerializedName("user_id")
    @Expose
    val userId: Int,

    @SerializedName("detail")
    @Expose
    val error: DetailDTO


)
