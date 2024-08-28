package com.example.test_message.pro.domain

class SendAuthCodeUseCase
    (private val repository: AppRepository)
{

   suspend operator fun invoke(phone: PhoneUserEntity){
        return repository.sendAuthCodeUseCase(phone)
    }


}