package com.example.test_message.pro.data.network.putInfo


import com.example.test_message.pro.data.network.authDTO.DetailDTO
import com.example.test_message.pro.data.network.profileDTO.AvatarGetDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponsePutDTO (

    @SerializedName("avatars")
    @Expose
    var avatars: AvatarGetDTO? = null,

    @SerializedName("detail")
    @Expose
    var detail: List<DetailDTO> = ArrayList()
)