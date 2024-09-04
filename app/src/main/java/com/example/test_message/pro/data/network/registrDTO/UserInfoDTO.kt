package com.example.test_message.pro.data.network.registrDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfoDTO(

    @SerializedName("phone")
    @Expose
    val phone: String?,

    @SerializedName("name")
    @Expose
    val userName:String?,

    @SerializedName("username")
    @Expose
    val name:String?
)
