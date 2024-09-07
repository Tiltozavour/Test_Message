package com.example.test_message.pro.data.network


import com.example.test_message.pro.di.ApplicationScope
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ApplicationScope
object ApiFactory {
    private const val BASE_URL = "https://plannerok.ru/docs/"


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    val apiService = retrofit.create(ApiService::class.java)



}


