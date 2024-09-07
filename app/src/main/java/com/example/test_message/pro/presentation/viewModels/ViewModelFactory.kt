package com.example.test_message.pro.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.pro.di.ApplicationScope
import javax.inject.Provider
import javax.inject.Inject


@ApplicationScope
class ViewModelFactory @Inject constructor(
    private val viewModelProvider:@JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProvider[modelClass]?.get() as T
    }

    }


