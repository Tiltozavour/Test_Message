package com.example.test_message.pro.data.network.checkDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhoneCodeDTO (

    @SerializedName("phone")
    @Expose
    val phone:String,

    @SerializedName("code")
    @Expose
    val code:String

)