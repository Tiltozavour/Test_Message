package com.example.test_message.pro.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.data.network.profileDTO.ProfileDataDTO
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo

import com.example.test_message.pro.domain.usecases.GetListChatUseCase
import com.example.test_message.pro.domain.usecases.GetProfileInfoUseCase
import com.example.test_message.pro.domain.usecases.SaveInfoUserUseCase
import kotlinx.coroutines.async


class ChatViewModel:ViewModel() {

    private val repository = AppRepositoryImpl

    private val getChatList = GetListChatUseCase(repository)
    private val getProfileInfo = GetProfileInfoUseCase(repository)
    private val inputInfo = SaveInfoUserUseCase(repository)

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile:LiveData<UserProfile>
        get() = _userProfile

    private val _userInput = MutableLiveData<UserPutInfo>()
    val userInput:LiveData<UserPutInfo>
        get() = _userInput




    val chatList = getChatList.invoke()

    suspend fun getProfileInfo(){
        viewModelScope.async {
         _userProfile.value = getProfileInfo.invoke()
        }.await()
    }

    suspend fun inputInfo(userPutInfo: UserPutInfo){
        viewModelScope.async {
           inputInfo.invoke(userPutInfo)
        }.await()
    }







}