package com.example.test_message.pro.domain

import androidx.lifecycle.LiveData
import com.example.test_message.pro.data.network.profileDTO.ProfileDataDTO
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity

interface AppRepository {

   suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity):Boolean

   suspend fun checkAuthCodeUseCase(phoneCode: PhoneCode):Boolean

   suspend fun registrationUseCase(userInfo: UserInfoEntity)

   fun getListChatUseCase(): LiveData<List<ChatEntity>>

   suspend fun getProfileInfoUseCase():UserProfile





}