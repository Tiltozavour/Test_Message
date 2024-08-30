package com.example.test_message.pro.data.network.mapper

import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.registrDTO.UserInfoDTO
import com.example.test_message.pro.domain.entity.PhoneCode
import com.example.test_message.pro.domain.entity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.UserInfoEntity

class AppMapper {

    fun mapEntityToDTO(phoneUser: PhoneUserEntity):PhoneDTO{
        return PhoneDTO(
            phone = phoneUser.phone,
        )
    }

    fun mapEntityToCodeDTO(phoneCode: PhoneCode):PhoneCodeDTO{
        return PhoneCodeDTO(
            phone = phoneCode.phone,
            code = phoneCode.phoneCode
        )
    }
    fun mapEntityToUserInfoDTO(user: UserInfoEntity):UserInfoDTO{
        return UserInfoDTO(
            phone = user.phone,
            userName = user.userName,
            name = user.name
        )
    }


}