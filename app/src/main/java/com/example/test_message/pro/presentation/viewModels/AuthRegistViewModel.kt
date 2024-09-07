package com.example.test_message.pro.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.usecases.RegistrationUseCase
import com.example.test_message.pro.domain.usecases.SendAuthCodeUseCase
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.domain.usecases.CheckAuthCodeUseCase
import kotlinx.coroutines.async
import javax.inject.Inject

class AuthRegistViewModel @Inject constructor(
    private val sendAuthCodeUseCase:SendAuthCodeUseCase,
    private val checkAuthCodeUseCase :CheckAuthCodeUseCase,
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    suspend fun authorization(phone: String):Boolean {
        val phoneUser = PhoneUserEntity(phone)
        val answer = viewModelScope.async {
            sendAuthCodeUseCase.invoke(phoneUser)
        }.await()
        return answer
    }

     suspend fun checkAuthCode(phone: String, code:String):Boolean{
        val phoneCode = PhoneCode(phone,code)
        val answer = viewModelScope.async {
           checkAuthCodeUseCase.invoke(phoneCode)
        }.await()
        return answer

    }

    suspend fun registration(userInfo: UserInfoEntity):Boolean {
        val answer =  viewModelScope.async {
          registrationUseCase.invoke(userInfo)
        }.await()
        return answer
    }


}