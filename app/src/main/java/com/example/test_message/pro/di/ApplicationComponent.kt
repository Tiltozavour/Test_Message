package com.example.test_message.pro.di

import android.app.Application
import com.example.test_message.pro.presentation.activityEntry.StartActivity
import com.example.test_message.pro.presentation.activityEntry.authorization.AuthCodeFragment
import com.example.test_message.pro.presentation.activityEntry.logIn.LogInFragment
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

    fun inject(startActivity: StartActivity)
    fun inject(logInFragment: LogInFragment)
    fun inject(authenticationFragment: AuthCodeFragment)


    /*

    fun inject(registrationFragment: RegistrationFragment)
    fun inject(activityMain: StartActivity)
    fun inject(profileActivity: ProfileActivity)
    fun inject(chatActivity: ChatActivity)
    fun inject(dialogChatActivity: DialogChatActivity)
    fun inject(fragmentSettings: SettingsFragment)*/


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }

}