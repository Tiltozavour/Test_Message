package com.example.test_message.pro.data.network.putInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OutputDataDTO(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("username")
    @Expose
    var username: String? = null,

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null,

    @SerializedName("city")
    @Expose
    var city: String? = null,

    @SerializedName("vk")
    @Expose
    var vk: String? = null,

    @SerializedName("instagram")
    @Expose
    var instagram: String? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("avatar")
    @Expose
    var avatar: AvatarPutDTO? = null,

    )


