package com.example.test_message.pro.domain.entity.chatEntity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Avatars(
    @SerializedName("avatar")
    @Expose
    val avatar: String,
    @SerializedName("bigAvatar")
    @Expose
    val bigAvatar: String,
    @SerializedName("miniAvatar")
    @Expose
    val miniAvatar: String
)
