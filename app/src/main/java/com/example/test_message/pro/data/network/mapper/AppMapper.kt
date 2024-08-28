package com.example.test_message.pro.data.network.mapper

import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.domain.PhoneUserEntity

class AppMapper {

    fun mapEntityToDTO(phoneUser:PhoneUserEntity):PhoneDTO{
        return PhoneDTO(
            phone = phoneUser.phone
        )
    }


}