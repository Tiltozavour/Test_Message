package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo
import javax.inject.Inject

class SaveInfoUserUseCase @Inject constructor
    (private val repository: AppRepository) {

    suspend operator fun invoke(userPutInfo: UserPutInfo){
        repository.saveInfoUserUseCase(userPutInfo)
    }

}