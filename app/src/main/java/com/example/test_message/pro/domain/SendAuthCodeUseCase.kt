package com.example.test_message.pro.domain

class SendAuthCodeUseCase
    (private val repository: AppRepository)
{

   suspend operator fun invoke(phone: PhoneUserEntity):Boolean {
        return repository.sendAuthCodeUseCase(phone)
    }


}