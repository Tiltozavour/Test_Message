package com.example.test_message.pro.domain.entity.chatEntity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    var name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String?,
    val id: Int?,
    val last: String?,
    val online: Boolean?,
    val created: String?,
    val phone: String?,
    val completedTask: Int?,
    val avatars: AvatarGet
):Parcelable