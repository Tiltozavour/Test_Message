package com.example.test_message.pro.data.network.checkDTO

import com.example.test_message.pro.data.network.authDTO.DetailDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckResponseDTO(
    @SerializedName("is_success")
    @Expose
    val successful: TokenDTO,

    @SerializedName("detail")
    @Expose
    val error: DetailDTO
)
