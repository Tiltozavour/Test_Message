package com.example.test_message.pro.data.network.profileDTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvatarGetDTO(

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null,

    @SerializedName("bigAvatar")
    @Expose
    var bigAvatar: String? = null,

    @SerializedName("miniAvatar")
    @Expose
    var miniAvatar: String? = null

)


