package com.example.test_message.pro.presentation.old.viewModels

import android.app.Application
import com.example.test_message.pro.di.DaggerApplicationComponent


class MessageApp:Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}