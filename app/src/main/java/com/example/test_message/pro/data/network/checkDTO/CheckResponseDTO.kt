package com.example.test_message.pro.data.network.checkDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckResponseDTO(

    @SerializedName("refresh_token")
    @Expose
    val refreshToken: String?,

    @SerializedName("access_token")
    @Expose
    val accessToken: String?,

    @SerializedName("is_user_exists")
    @Expose
    val isUserExists: Boolean?,

    @SerializedName("user_id")
    @Expose
    val userId: Int?
)
