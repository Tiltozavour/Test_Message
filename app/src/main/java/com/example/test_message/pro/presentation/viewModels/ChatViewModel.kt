package com.example.test_message.pro.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.test_message.pro.data.AppRepositoryImpl

import com.example.test_message.pro.domain.usecases.GetListChatUseCase


class ChatViewModel:ViewModel() {

    private val repository = AppRepositoryImpl

    private val getChatList = GetListChatUseCase(repository)

    val chatList = getChatList.invoke()




}