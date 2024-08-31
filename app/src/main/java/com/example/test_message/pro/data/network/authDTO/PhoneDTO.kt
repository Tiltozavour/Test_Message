package com.example.test_message.pro.data.network.authDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhoneDTO (

    @SerializedName("phone")
    @Expose
    val  phone:String
)