package com.example.test_message.pro.domain

import android.provider.ContactsContract.CommonDataKinds.Phone

data class UserInfoEntity (
    val phone:Phone,
    val userName:String,
    val name:String
)