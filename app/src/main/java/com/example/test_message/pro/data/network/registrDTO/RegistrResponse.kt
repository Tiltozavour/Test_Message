package com.example.test_message.pro.data.network.registrDTO

import com.example.test_message.pro.data.network.authDTO.Detail
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegistrResponse(

    @SerializedName("refresh_token")
    @Expose
    var refreshToken: String? = null,

    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null,

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null,

    @SerializedName("detail")
    @Expose
    var detail: List<Detail> = ArrayList()
)
