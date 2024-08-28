package com.example.test_message.pro.data.network


import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.authDTO.AuthResponseDTO
import com.example.test_message.pro.data.network.checkDTO.CheckResponseDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.registrDTO.RegistrResponse
import com.example.test_message.pro.data.network.registrDTO.UserInfoDTO
import com.example.test_message.pro.data.network.registrationEntity.ServResponse
import com.example.test_message.pro.data.network.registrationEntity.UserInfo
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {



    //присылает проверочный код
    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body phoneDTO: PhoneDTO): Response<AuthResponseDTO>

    //проверяет авторизацию
    @POST("/api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body phoneCodeDTO: PhoneCodeDTO):Response<CheckResponseDTO>

    //регистрация нового пользовотеля
    @POST("/api/v1/users/register/")
    suspend fun getRegistration(@Body userInfoDTO: UserInfoDTO): Response<RegistrResponse>

}

//
