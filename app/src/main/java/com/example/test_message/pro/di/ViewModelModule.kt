package com.example.test_message.pro.di

import androidx.lifecycle.ViewModel
import com.example.test_message.pro.presentation.viewModels.AuthRegistViewModel
import com.example.test_message.pro.presentation.viewModels.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthRegistViewModel::class)
    fun bindsAuthRegistViewModel(viewModel:AuthRegistViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    fun bindsChatViewModel(viewModel:ChatViewModel):ViewModel

}