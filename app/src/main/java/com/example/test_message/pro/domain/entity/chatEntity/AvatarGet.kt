package com.example.test_message.pro.domain.entity.chatEntity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AvatarGet(

    val avatar: String?,
    val bigAvatar: String?,
    val miniAvatar: String?
):Parcelable
