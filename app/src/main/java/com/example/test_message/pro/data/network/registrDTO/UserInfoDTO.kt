package com.example.test_message.pro.data.network.registrDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfoDTO (

     @SerializedName("phone")
     @Expose
     var phone: String? = null,

     @SerializedName("name")
     @Expose
     var name: String? = null,

     @SerializedName("username")
     @Expose
     var username: String? = null
)

