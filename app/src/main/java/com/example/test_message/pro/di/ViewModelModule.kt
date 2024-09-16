package com.example.test_message.pro.di

import androidx.lifecycle.ViewModel
import com.example.test_message.pro.presentation.activityEntry.authorization.AuthorizationViewModel
import com.example.test_message.pro.presentation.activityEntry.logIn.LogInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun bindsLogInViewModel(viewModel:LogInViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindsAuthViewModel(viewModel:AuthorizationViewModel):ViewModel

}