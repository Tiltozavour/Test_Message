package com.example.test_message.pro.data.network.profileDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvatarGetDTO(
    @SerializedName("avatar")
    @Expose
    val avatar: String?,
    @SerializedName("bigAvatar")
    @Expose
    val bigAvatar: String?,
    @SerializedName("miniAvatar")
    @Expose
    val miniAvatar: String?
)