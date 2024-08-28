package com.example.test_message.pro.data.network


import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.authDTO.AuthResponseDTO
import com.example.test_message.pro.data.network.registrationEntity.ServResponse
import com.example.test_message.pro.data.network.registrationEntity.UserInfo
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {




    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body phoneDTO: PhoneDTO): Response<AuthResponseDTO>

    @POST("/api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body phoneDTO: PhoneDTO, code:String): Response<AuthResponseDTO>



    @POST("/api/v1/users/register/")
    suspend fun getAutorization(@Body userInfo: UserInfo): Response<ServResponse>

}

//
