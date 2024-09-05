package com.example.test_message.pro.data.network.authDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailArrayDTO(
    @SerializedName("detail")
    @Expose
    val detail:List<Detail> = ArrayList<Detail>()

)