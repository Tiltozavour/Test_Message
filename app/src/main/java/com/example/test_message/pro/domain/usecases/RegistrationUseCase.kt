package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity

class RegistrationUseCase(
    private val repository: AppRepository
) {
    operator suspend fun invoke(userInfo: UserInfoEntity){
        repository.registrationUseCase(userInfo)
    }

}
