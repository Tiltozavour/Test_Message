package com.example.test_message.pro.domain

import com.example.test_message.pro.domain.entity.PhoneCode
import com.example.test_message.pro.domain.entity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.UserInfoEntity

interface AppRepository {

   suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity):Boolean

   suspend fun checkAuthCodeUseCase(phoneCode: PhoneCode):Boolean

   suspend fun registrationUseCase(userInfo: UserInfoEntity)





}