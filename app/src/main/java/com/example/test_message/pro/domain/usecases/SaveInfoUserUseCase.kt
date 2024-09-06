package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo

class SaveInfoUserUseCase(private val repository: AppRepository) {

    suspend operator fun invoke(userPutInfo: UserPutInfo):Boolean{
      return repository.saveInfoUserUseCase(userPutInfo)
    }

}