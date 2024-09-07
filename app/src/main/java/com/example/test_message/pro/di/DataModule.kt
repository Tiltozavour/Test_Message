package com.example.test_message.pro.di

import android.app.Application
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.ApiService
import com.example.test_message.pro.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindsRepository(impl:AppRepositoryImpl):AppRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideApiService():ApiService{
            return ApiFactory.apiService
        }
    }


}
