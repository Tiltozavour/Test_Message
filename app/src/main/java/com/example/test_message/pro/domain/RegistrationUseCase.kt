package com.example.test_message.pro.domain

class RegistrationUseCase(
    private val repository: AppRepository
) {
    operator suspend fun invoke(userInfo: UserInfoEntity){
        repository.registrationUseCase(userInfo)
    }

}
