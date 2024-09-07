package com.example.test_message.pro.di

import android.app.Application
import com.example.test_message.pro.presentation.chatProfileActivity.ChatActivity
import com.example.test_message.pro.presentation.chatProfileActivity.DialogChatActivity
import com.example.test_message.pro.presentation.chatProfileActivity.ProfileActivity
import com.example.test_message.pro.presentation.chatProfileActivity.SettingsFragment
import com.example.test_message.pro.presentation.loginAndRegisActivity.AuthCodeFragment
import com.example.test_message.pro.presentation.loginAndRegisActivity.MainActivity
import com.example.test_message.pro.presentation.loginAndRegisActivity.RegistrationFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activityMain:MainActivity)
    fun inject(fragmentAuth: AuthCodeFragment)
    fun inject(fragmentRegist: RegistrationFragment)
    fun inject(profileActivity: ProfileActivity)
    fun inject(chatActivity:ChatActivity)
    fun inject(dialogChatActivity:DialogChatActivity)
    fun inject(fragmentSettings: SettingsFragment)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }


}