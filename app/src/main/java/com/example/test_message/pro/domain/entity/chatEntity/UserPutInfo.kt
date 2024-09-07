package com.example.test_message.pro.domain.entity.chatEntity


data class UserPutInfo(
    val name: String,
    val username: String?,
    val birthday: String?,
    val city: String?,
    val vk: String?,
    val instagram: String?,
    val status: String?,
    val avatar: AvatarPut?
)
