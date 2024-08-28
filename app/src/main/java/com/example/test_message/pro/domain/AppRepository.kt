package com.example.test_message.pro.domain

interface AppRepository {

   suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity)



}