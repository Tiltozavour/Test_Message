package com.example.test_message.pro.domain.usecases

import com.example.test_message.pro.data.network.profileDTO.ProfileDataDTO
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile

class GetProfileInfoUseCase(
    private val repository: AppRepository
)
{

    suspend fun invoke():UserProfile{
      return repository.getProfileInfoUseCase()
    }

}