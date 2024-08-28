package com.example.test_message.pro.domain

import android.provider.ContactsContract.CommonDataKinds.Phone

class CheckAuthorizationUseCase
    (private val repository: AppRepository)
{

   suspend fun checkAuthorization(phone: PhoneUserEntity,code:Int):Boolean {
        return repository.checkAuthorization(phone,code)
    }


}