package com.example.test_message.pro.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.domain.entity.UserProfile

import com.example.test_message.pro.domain.usecases.GetListChatUseCase
import com.example.test_message.pro.domain.usecases.GetProfileInfoUseCase
import kotlinx.coroutines.async


class ChatViewModel:ViewModel() {

    private val repository = AppRepositoryImpl

    private val getChatList = GetListChatUseCase(repository)
    private val getProfileInfo = GetProfileInfoUseCase(repository)

    val chatList = getChatList.invoke()

    suspend fun getProfileInfo():UserProfile{
        val answer = viewModelScope.async {
            getProfileInfo.invoke()
        }.await()
        return answer
    }




}