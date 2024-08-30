package com.example.test_message.pro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.domain.entity.PhoneCode
import com.example.test_message.pro.domain.entity.PhoneUserEntity
import com.example.test_message.pro.domain.usecases.RegistrationUseCase
import com.example.test_message.pro.domain.usecases.SendAuthCodeUseCase
import com.example.test_message.pro.domain.entity.UserInfoEntity
import com.example.test_message.pro.domain.usecases.CheckAuthCodeUseCase
import kotlinx.coroutines.launch

class AuthRegistViewModel : ViewModel() {


    private val repository = AppRepositoryImpl

    private val sendAuthCodeUseCase = SendAuthCodeUseCase(repository)
    private val checkAuthCodeUseCase = CheckAuthCodeUseCase(repository)
    private val registrationUseCase = RegistrationUseCase(repository)




    fun authorization(phone: String):Boolean {
        val phoneUser = PhoneUserEntity(phone)
        var answer = false
        viewModelScope.launch {
         answer  = sendAuthCodeUseCase.invoke(phoneUser)
        }
        return answer
    }

    fun checkAuthCode(phone: String, code:String):Boolean{
        val phoneCode = PhoneCode(phone,code)
        var answer = false
        viewModelScope.launch {
            answer  = checkAuthCodeUseCase.invoke(phoneCode)
        }
        return answer

    }


    fun registration(userInfo: UserInfoEntity) {
        viewModelScope.launch {
            registrationUseCase.invoke(userInfo)
        }
    }


}