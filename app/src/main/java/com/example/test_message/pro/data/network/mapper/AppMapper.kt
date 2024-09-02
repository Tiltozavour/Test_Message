package com.example.test_message.pro.data.network.mapper

import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.profileDTO.AvatarsDTO
import com.example.test_message.pro.data.network.profileDTO.ProfileDataDTO
import com.example.test_message.pro.data.network.registrDTO.UserInfoDTO
import com.example.test_message.pro.domain.entity.chatEntity.Avatars
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity

class AppMapper {

    fun mapEntityToDTO(phoneUser: PhoneUserEntity): PhoneDTO {
        return PhoneDTO(
            phone = phoneUser.phone,
        )
    }

    fun mapEntityToCodeDTO(phoneCode: PhoneCode): PhoneCodeDTO {
        return PhoneCodeDTO(
            phone = phoneCode.phone,
            code = phoneCode.phoneCode
        )
    }

    fun mapEntityToUserInfoDTO(user: UserInfoEntity): UserInfoDTO {
        return UserInfoDTO(
            phone = user.phone,
            userName = user.userName,
            name = user.name
        )
    }


    fun profileDTOToEntity(profileData: ProfileDataDTO): UserProfile {

        return UserProfile(
            name = profileData.name,
            username = profileData.username,
            birthday = profileData.birthday,
            city = profileData.city,
            vk = profileData.vk,
            instagram = profileData.instagram,
            status = profileData.status,
            avatar = profileData.avatar,
            id = profileData.id,
            last = profileData.last,
            online = profileData.online,
            created = profileData.created,
            phone = profileData.phone,
            completedTask = profileData.completedTask,
            avatars = profileData.avatars
            )
    }



}