package com.example.test_message.pro.data

import android.util.Log
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.data.network.registrationEntity.UserInfo
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.PhoneUserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object AppRepositoryImpl:AppRepository{

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()


    override suspend fun checkAuthorization(phone: PhoneUserEntity, code: Int): Boolean {
     val resp =  apiService.sendAuthCode(mapper.mapEntityToDTO(phone)) //отсылаем телефон
        Log.d("testApi", resp.toString())
        TODO("Not yet implemented")
    }
}


private fun test() {


    CoroutineScope(Dispatchers.IO).launch {

        val phoneDTO = PhoneDTO("+79219999999.")

        val userOne = UserInfo("748543", "Anna", "Tita")
        //val resp = ApiFactory.apiService.getAutorization(userOne)
        val auth = ApiFactory.apiService.sendAuthCode(phoneDTO)

    }

}