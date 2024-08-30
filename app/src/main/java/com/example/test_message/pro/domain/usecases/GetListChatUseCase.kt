package com.example.test_message.pro.domain.usecases

import androidx.lifecycle.LiveData
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.ChatEntity

class GetListChatUseCase(
    private val repository: AppRepository
)
{
    operator fun invoke(): LiveData<List<ChatEntity>>{
        return repository.getListChatUseCase()
    }

}