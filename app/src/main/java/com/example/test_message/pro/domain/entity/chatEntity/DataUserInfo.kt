package com.example.test_message.pro.domain.entity.chatEntity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataUserInfo(
    @SerializedName("profile_data")
    @Expose
val profileData: UserProfile
)


