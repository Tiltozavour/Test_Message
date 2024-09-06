package com.example.test_message.pro.data.network


import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.authDTO.AuthResponseDTO
import com.example.test_message.pro.data.network.checkDTO.CheckResponseDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.checkDTO.RefreshTokenDTO
import com.example.test_message.pro.data.network.profileDTO.AvatarGetDTO
import com.example.test_message.pro.data.network.profileDTO.DataUserInfoDTO
import com.example.test_message.pro.data.network.putInfo.OutputDataDTO
import com.example.test_message.pro.data.network.registrDTO.RegistrResponse
import com.example.test_message.pro.data.network.registrDTO.UserInfoDTO
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {


    //присылает проверочный код
    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body phoneDTO: PhoneDTO): Response<AuthResponseDTO>

    //проверяет авторизацию
    @POST("/api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body phoneCodeDTO: PhoneCodeDTO):Response<CheckResponseDTO>

    //регистрация нового пользователя
    @POST("/api/v1/users/register/")
    suspend fun getRegistration(@Body userInfoDTO: UserInfoDTO): Response<RegistrResponse>

    //получение информации о пользователе
    @GET("/api/v1/users/me/")
    suspend fun getInfoUser(
        @Header("Authorization") token: String,
    ):Response<DataUserInfoDTO>

    //отправка новых данных
    @PUT("/api/v1/users/me/")
    suspend fun putInfoUser(
        @Header("Authorization") token: String,
        @Body outputDataDTO: OutputDataDTO
    ):Response<AvatarGetDTO>

    //Рефреш данных
    @GET("/api/v1/users/me/")
    suspend fun refreshToken(
        @Header("Authorization") token: String,
        @Body refreshToken:String
    ):Response<RefreshTokenDTO>


}


