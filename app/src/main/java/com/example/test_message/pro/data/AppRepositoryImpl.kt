package com.example.test_message.pro.data


import android.util.Log
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.PhoneUserEntity
import com.example.test_message.pro.domain.UserInfoEntity


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()


    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity): Boolean {
        val resp = apiService.sendAuthCode(mapper.mapEntityToDTO(phone)) //отсылаем телефон
        val code = resp.code()  //получаем ответ сервера
        var answer = false
        when (code) {
            201 -> {
                answer = checkAuthCodeUseCase(mapper.mapEntityToCodeDTO(phone))
            }

            422 -> {
                answer = false
                Log.d("testApi", "неудачный чек телефона")
            }
        }
        return answer
    }

    private suspend fun checkAuthCodeUseCase(phone: PhoneCodeDTO): Boolean {
        val resp = apiService.checkAuthCode(phone)
        var answer = false
        if (resp.isSuccessful) {
            if (resp.body()?.isUserExists == true) {
                answer = true
                Log.d("testApi", "ха ха ура!${resp.body()}")
            } else {
                answer = false
                // registrationUseCase(UserInfoEntity(phone.phone,"Tita", "Tutazavr"))
            }
        } else Log.d("testApi", "неудачный чек аунтификации")
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

