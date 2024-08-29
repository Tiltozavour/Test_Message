package com.example.test_message.pro.data


import android.util.Log
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.checkDTO.CheckResponseDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.PhoneUserEntity
import com.example.test_message.pro.domain.UserInfoEntity


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()
    val user = UserInfoEntity("+79219999999", "Anna", "Tita")


    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity) {
        val resp = apiService.sendAuthCode(mapper.mapEntityToDTO(phone)) //отсылаем телефон
        val code = resp.code() //получаем ответ сервера
        when (code) {
            201 -> {
                checkAuthCodeUseCase(mapper.mapEntityToCodeDTO(phone))
            }

            422 -> Log.d("testApi", "неудачный чек телефона")
            else -> {
                TODO()
            }
        }
    }

    private suspend fun checkAuthCodeUseCase(phone: PhoneCodeDTO) {
        val resp = apiService.checkAuthCode(phone)
        if (resp.isSuccessful) {
            if (resp.body()?.isUserExists == true) {
                Log.d("testApi", "ха ха ура!${resp.body()}")
                TODO()
            } else {
                registrationUseCase(user)
            }
        } else Log.d("testApi", "неудачный чек аунтификации")
        TODO()
    }


    override suspend fun registrationUseCase(userInfo: UserInfoEntity) {
        val resp = apiService.getRegistration(mapper.mapEntityToUserInfoDTO(userInfo))
        if (resp.isSuccessful) {
            val token = resp.body()?.accessToken
            val refresh = resp.body()?.refreshToken
            Log.d("testApi", "${token.toString()},${refresh.toString()}")
        }
        else{
            val da = resp.code()
            Log.d("testApi", "Неудачная поптыка ${da}")
        }
    }
}

