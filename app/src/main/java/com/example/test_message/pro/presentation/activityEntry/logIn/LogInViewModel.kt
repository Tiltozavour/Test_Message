package com.example.test_message.pro.presentation.activityEntry.logIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.usecases.SendAuthCodeUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val sendAuthCode: SendAuthCodeUseCase,
): ViewModel() {

    data class CheckState(
        val isChecking: Boolean = false,
        val isSuccess: Boolean = false,
        val isError: Boolean = false
    )

    private val _checkState = MutableLiveData<CheckState>()
    val checkState: LiveData<CheckState>
        get() = _checkState


    fun checkPhoneValid(phone: PhoneUserEntity) {
        _checkState.value = CheckState(isChecking = true)
        val answer = viewModelScope.async {
            authorization(phone)
        }
        viewModelScope.launch {
            when(answer.await()){
                true -> {_checkState.value = CheckState(isSuccess = true)
                    defaultState()
                }
                false ->{_checkState.value = CheckState(isError = true) }
            }
        }
    }

    private fun defaultState() {
        _checkState.value = CheckState(isSuccess = false, isChecking = false, isError = false )
    }


    private suspend fun authorization(phone: PhoneUserEntity):Boolean {
        val answer = viewModelScope.async {
            sendAuthCode.invoke(phone)
        }.await()
        return answer
    }

}