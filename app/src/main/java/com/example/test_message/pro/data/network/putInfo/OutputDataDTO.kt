package com.example.test_message.pro.data.network.putInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OutputDataDTO(


    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("username")
    @Expose
    val username: String,

    @SerializedName("birthday")
    @Expose
    val birthday: String,

    @SerializedName("city")
    @Expose
    val city: String,

    @SerializedName("vk")
    @Expose
    val vk: String,
    @SerializedName("instagram")
    @Expose
    val instagram: String,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("avatar")
    @Expose
    val avatar: AvatarPutDTO,
)