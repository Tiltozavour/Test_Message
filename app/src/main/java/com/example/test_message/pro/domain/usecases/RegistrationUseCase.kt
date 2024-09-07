package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import javax.inject.Inject

class RegistrationUseCase @Inject constructor
    (private val repository: AppRepository)
{
    operator suspend fun invoke(userInfo: UserInfoEntity):Boolean{
      return repository.registrationUseCase(userInfo)
    }

}
