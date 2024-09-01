package com.example.test_message.pro.data.network.profileDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileDataDTO(

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
    val avatar: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("last")
    @Expose
    val last: String,
    @SerializedName("online")
    @Expose
    val online: Boolean,
    @SerializedName("created")
    @Expose
    val created: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("completed_task")
    @Expose
    val completedTask: Int,
    @SerializedName("avatars")
    @Expose
    val avatars: AvatarsDTO

)
