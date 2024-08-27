package com.example.test_message.pro.domain.AuthEntity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResponseAuth(

    @SerializedName("is_success")
    @Expose
    val successful: Boolean,

    @SerializedName("detail")
    @Expose
    val error: Detail

)
