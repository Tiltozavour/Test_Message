package com.example.test_message.pro.presentation

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.domain.ErrorEntity
import com.example.test_message.pro.domain.PhoneUserEntity
import com.example.test_message.pro.domain.RegistrationUseCase
import com.example.test_message.pro.domain.SendAuthCodeUseCase
import com.example.test_message.pro.domain.UserInfoEntity
import kotlinx.coroutines.launch

class AuthRegistViewModel : ViewModel() {


    private val repository = AppRepositoryImpl

    private val sendAuthCodeUseCase = SendAuthCodeUseCase(repository)
    private val registrationUseCase = RegistrationUseCase(repository)

/*

    private val _error = MutableLiveData<ErrorEntity>()
    val error: LiveData<ErrorEntity>
        get() = _error
*/


    fun authorization(phone: String):Boolean {
        val phoneUser = PhoneUserEntity(phone)
        var answer = false
        viewModelScope.launch {
         answer  = sendAuthCodeUseCase.invoke(phoneUser)
        }
        return answer
    }

    fun registration(userInfo: UserInfoEntity) {
        viewModelScope.launch {
            registrationUseCase.invoke(userInfo)
        }
    }


}