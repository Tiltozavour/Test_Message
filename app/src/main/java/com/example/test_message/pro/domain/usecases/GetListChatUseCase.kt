package com.example.test_message.pro.domain.usecases

import androidx.lifecycle.LiveData
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity
import javax.inject.Inject

class GetListChatUseCase @Inject constructor
    (private val repository: AppRepository)
{
    operator fun invoke(): LiveData<List<ChatEntity>>{
        return repository.getListChatUseCase()
    }

}