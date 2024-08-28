package com.example.test_message.pro.data.network.registrationEntity

import com.example.test_message.pro.data.network.authDTO.DetailDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailError(
    @SerializedName("detail")
    @Expose
    val detail:List<DetailDTO>  = ArrayList<DetailDTO>()
)
