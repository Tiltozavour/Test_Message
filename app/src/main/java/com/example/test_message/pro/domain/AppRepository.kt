package com.example.test_message.pro.domain

interface AppRepository {

   suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity):Boolean

   suspend fun registrationUseCase(userInfo: UserInfoEntity)



}