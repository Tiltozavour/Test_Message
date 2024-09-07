package com.example.test_message.pro.data.network.putInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvatarPutDTO(
    @SerializedName("filename")
    @Expose
    var filename: String? = null,

    @SerializedName("base_64")
    @Expose
    var base64: String? = null,

    )


