package com.example.test_message.pro.data.network.checkDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhoneCodeDTO(
    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("code")
    @Expose
    var code: String? = null,

    )

