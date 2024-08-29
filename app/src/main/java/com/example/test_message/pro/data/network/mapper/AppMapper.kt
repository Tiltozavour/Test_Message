package com.example.test_message.pro.data.network.mapper

import android.icu.text.IDNA.Info
import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.registrDTO.UserInfoDTO
import com.example.test_message.pro.domain.PhoneUserEntity
import com.example.test_message.pro.domain.UserInfoEntity

class AppMapper {

    fun mapEntityToDTO(phoneUser:PhoneUserEntity):PhoneDTO{
        return PhoneDTO(
            phone = phoneUser.phone,
        )
    }

    fun mapEntityToCodeDTO(phoneUser:PhoneUserEntity):PhoneCodeDTO{
        return PhoneCodeDTO(
            phone = phoneUser.phone,
            code = "133337"
        )
        TODO()
    }
    fun mapEntityToUserInfoDTO(user:UserInfoEntity):UserInfoDTO{
        return UserInfoDTO(
            phone = user.phone,
            userName = user.userName,
            name = user.name
        )
    }


}