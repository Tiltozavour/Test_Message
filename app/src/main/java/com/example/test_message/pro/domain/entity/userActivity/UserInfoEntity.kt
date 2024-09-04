package com.example.test_message.pro.domain.entity.userActivity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoEntity (

    @SerializedName("phone")
    @Expose
    val phone:String,

    @SerializedName("name")
    @Expose
    val name:String,

    @SerializedName("username")
    @Expose
    val userName:String
):Parcelable