package com.example.test_message.pro.presentation.activityEntry.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.usecases.CheckAuthCodeUseCase
import com.example.test_message.pro.domain.usecases.SendAuthCodeUseCase
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthorizationViewModel @Inject constructor(
    private val checkAuthCode: CheckAuthCodeUseCase
):ViewModel() {

    data class CheckState(
        val waitingCode:Boolean = true,
        val isChecking: Boolean = false,
        val isSuccess: Boolean = false,
        val isError: Boolean = false
    )

    private val _checkState = MutableLiveData<CheckState>()
    val checkState: LiveData<CheckState>
        get() = _checkState


    fun checkPhoneWithCode(phone: PhoneCode){
        _checkState.value = CheckState(isChecking = true, waitingCode = false)
       val answer = viewModelScope.async {
            checkingExistingPhone(phone)
        }
        viewModelScope.launch {
            when(answer.await()){
                true -> {
                    _checkState.value = CheckState(isSuccess = true, waitingCode = false)

                }
                false ->{_checkState.value = CheckState(isError = true, waitingCode = false)}
            }
        }
    }

    private suspend fun checkingExistingPhone(phone: PhoneCode):Boolean{
        val answer = viewModelScope.async {
            checkAuthCode.invoke(phone)
        }.await()
        return answer
    }


}