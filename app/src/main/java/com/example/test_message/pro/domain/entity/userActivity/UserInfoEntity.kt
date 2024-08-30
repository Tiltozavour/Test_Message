package com.example.test_message.pro.domain.entity.userActivity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoEntity (
    val phone:String,
    val userName:String,
    val name:String
):Parcelable