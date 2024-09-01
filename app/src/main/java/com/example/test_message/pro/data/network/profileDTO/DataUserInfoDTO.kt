package com.example.test_message.pro.data.network.profileDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataUserInfoDTO (

    @SerializedName("profile_data")
    @Expose
    val profileData:ProfileDataDTO
)