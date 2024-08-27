package com.example.test_message.pro.data.network


import com.example.test_message.pro.domain.AuthEntity.Phone
import com.example.test_message.pro.domain.AuthEntity.ServerResponseAuth
import com.example.test_message.pro.domain.RegistrationEntity.ServResponse
import com.example.test_message.pro.domain.RegistrationEntity.UserInfo
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body phone: Phone): Response<ServerResponseAuth>

    @POST("/api/v1/users/register/")
    suspend fun getAutorization(@Body userInfo: UserInfo): Response<ServResponse>

}

//
