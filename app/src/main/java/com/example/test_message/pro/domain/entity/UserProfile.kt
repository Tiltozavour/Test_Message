package com.example.test_message.pro.domain.entity

import com.example.test_message.pro.data.network.profileDTO.AvatarsDTO

data class UserProfile(
    var name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val id: Int,
    val last: String,
    val online: Boolean,
    val created: String,
    val phone: String,
    val completedTask: Int,
    val avatars: Avatars
)