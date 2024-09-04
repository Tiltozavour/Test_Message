package com.example.test_message.pro.data.network.putInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvatarPutDTO(

    @SerializedName("filename")
    @Expose
    val filename: String,

    @SerializedName("base_64")
    @Expose
    val base64: String,
    )
