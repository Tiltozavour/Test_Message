package com.example.test_message.pro.data.network.registrDTO

import android.provider.ContactsContract.CommonDataKinds.Phone

data class UserInfoDTO(

    val phone: Phone,
    val userName:String,
    val name:String
)
