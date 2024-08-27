package com.example.test_message.pro.domain.AuthEntity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Detail(


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