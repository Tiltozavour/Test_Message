package com.example.test_message.pro.data


import android.util.Log
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.checkDTO.CheckResponseDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.PhoneUserEntity


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()


    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity){

        val resp = apiService.sendAuthCode(mapper.mapEntityToDTO(phone)) //отсылаем телефон
        val code = resp.code() //получаем ответ сервера


         when (code) {
            201 -> {
                checkAuthCodeUseCase(mapper.mapEntityToCodeDTO(phone))
            }
            422 -> Log.d("testApi", "неудачный чек телефона")
            else -> {

            }
        }
    }

    private suspend fun checkAuthCodeUseCase(phone: PhoneCodeDTO){
        val resp =  apiService.checkAuthCode(phone)
        if (resp.isSuccessful){
            if (resp.body()?.isUserExists == true){
                Log.d("testApi", "ха ха ура!${resp.body()}" )
            }
            else{

            }
        }
        else Log.d("testApi", "неудачный чек аунтификации")
    }



}

