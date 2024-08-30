package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode

class CheckAuthCodeUseCase (private val repository: AppRepository)
{

    suspend operator fun invoke(phoneCode: PhoneCode):Boolean {
       return repository.checkAuthCodeUseCase(phoneCode)
    }

}