package com.example.test_message.pro.data.network.authDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailDTO(

    @SerializedName("loc")
    @Expose
    val loc:List<String> = ArrayList<String>(),

    @SerializedName("msg")
    @Expose
    val message:String,

    @SerializedName("type")
    @Expose
    val type:String


)