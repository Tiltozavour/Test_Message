package com.example.test_message.pro.data


import android.util.Log
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.PhoneCode
import com.example.test_message.pro.domain.entity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.UserInfoEntity


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()


    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity): Boolean {
        val resp = apiService.sendAuthCode(mapper.mapEntityToDTO(phone)) //отсылаем телефон
        val code = resp.code()  //получаем ответ сервера
        var answer = false
        when (code) {
            201 -> {
                answer = true
                Log.d("testApi", "удачный чек телефона")
            }
            422 -> {
                answer = false
                Log.d("testApi", "неудачный чек телефона")
            }
        }
        return answer
    }


    override suspend fun checkAuthCodeUseCase(phoneCode: PhoneCode): Boolean {
        val resp = apiService.checkAuthCode(mapper.mapEntityToCodeDTO(phoneCode))
        val answer: Boolean
        if (resp.isSuccessful) {
            Log.d("testApi", "ха ха ура!${resp.body()!!.accessToken}")
            if (resp.body()?.isUserExists == true) {
                answer = true
            } else {
                answer = false
            }
        }
        else answer = false
        Log.d("testApi", "неудачный чек аунтификации")
        return answer
    }



    override suspend fun registrationUseCase(userInfo: UserInfoEntity) {
        val resp = apiService.getRegistration(mapper.mapEntityToUserInfoDTO(userInfo))
        if (resp.isSuccessful) {
            val token = resp.body()?.accessToken
            val refresh = resp.body()?.refreshToken
            Log.d("testApi", "${token.toString()},${refresh.toString()}")
        } else {
            val da = resp.code()
            Log.d("testApi", "Неудачная поптыка регистрации ${da}")
        }
    }
}

