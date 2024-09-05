package com.example.test_message.pro.domain.entity.userActivity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoEntity (
    val phone:String,
    val name:String,
    val userName:String
):Parcelable