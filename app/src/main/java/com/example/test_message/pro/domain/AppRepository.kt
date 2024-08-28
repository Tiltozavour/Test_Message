package com.example.test_message.pro.domain

interface AppRepository {

   suspend fun checkAuthorization(phone: PhoneUserEntity,code:Int):Boolean

}