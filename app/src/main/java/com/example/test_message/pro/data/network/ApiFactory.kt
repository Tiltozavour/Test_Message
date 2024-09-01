package com.example.test_message.pro.data.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiFactory {
    private const val BASE_URL = "https://plannerok.ru/docs/"

/*
 var client = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization",  "Bearer${token}")
            .build()
        chain.proceed(request)
    }).build()
*/


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)



}


