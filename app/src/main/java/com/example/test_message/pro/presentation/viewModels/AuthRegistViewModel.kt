package com.example.test_message.pro.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.usecases.RegistrationUseCase
import com.example.test_message.pro.domain.usecases.SendAuthCodeUseCase
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.domain.usecases.CheckAuthCodeUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

     suspend fun checkAuthCode(phone: String, code:String):Boolean{
        val phoneCode = PhoneCode(phone,code)
        val answer = viewModelScope.async {
           checkAuthCodeUseCase.invoke(phoneCode)
        }.await()
        return answer

    }

    fun registration(userInfo: UserInfoEntity) {
        viewModelScope.launch {
            registrationUseCase.invoke(userInfo)
        }
    }


}