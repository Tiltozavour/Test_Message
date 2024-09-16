package com.example.test_message.pro.data.network.authDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class AuthResponseDTO(
    @SerializedName("is_success")
    @Expose
    var isSuccess: Boolean? = null,

    @SerializedName("detail")
    @Expose
    var detail: List<DetailDTO> = ArrayList(),

    )

