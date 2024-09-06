package com.example.test_message.pro.domain.entity.chatEntity

import com.example.test_message.pro.data.network.putInfo.AvatarPutDTO

data class UserPutInfo(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: AvatarPut?
)
