package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity

class SendAuthCodeUseCase
    (private val repository: AppRepository)
{

   suspend operator fun invoke(phone: PhoneUserEntity):Boolean {
        return repository.sendAuthCodeUseCase(phone)
    }


}