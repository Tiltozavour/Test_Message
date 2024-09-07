package com.example.test_message.pro.data.network.authDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Detail(
    @SerializedName("loc")
    @Expose
    var loc: List<String> = ArrayList(),

    @SerializedName("msg")
    @Expose
    var msg: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    )
