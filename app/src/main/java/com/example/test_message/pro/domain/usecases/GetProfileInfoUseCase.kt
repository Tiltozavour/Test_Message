package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.UserProfile

class GetProfileInfoUseCase(
    private val repository: AppRepository
)
{

    suspend fun invoke():UserProfile{
      return repository.getProfileInfoUseCase()
    }

}