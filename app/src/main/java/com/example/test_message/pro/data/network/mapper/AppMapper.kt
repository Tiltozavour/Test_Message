package com.example.test_message.pro.data.network.mapper

import com.example.test_message.pro.data.network.authDTO.PhoneDTO
import com.example.test_message.pro.data.network.checkDTO.PhoneCodeDTO
import com.example.test_message.pro.data.network.profileDTO.ProfileDataDTO
import com.example.test_message.pro.data.network.putInfo.AvatarPutDTO
import com.example.test_message.pro.data.network.putInfo.OutputDataDTO
import com.example.test_message.pro.data.network.registrDTO.UserInfoDTO
import com.example.test_message.pro.domain.entity.chatEntity.AvatarGet
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo
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
            name = user.name,
            userName = user.userName

        )
    }

    fun profileDTOToEntity(profileData: ProfileDataDTO): UserProfile {
        return UserProfile(
            name = profileData.name ?: "Как твоё имя, воин?",
            username = profileData.username,
            birthday = profileData.birthday ?: "Ваше день рождение",
            city = profileData.city ?: "Ваш город",
            vk = profileData.vk ?: "Соц сеть(вк)",
            instagram = profileData.instagram ?: "Соц сеть(insta)",
            status = profileData.status ?: "Вот что я хотел бы вам всем тут сказать...",
            avatar = profileData.avatar,
            id = profileData.id,
            last = profileData.last,
            online = profileData.online,
            created = profileData.created,
            phone = profileData.phone,
            completedTask = profileData.completedTask,
            avatars = AvatarGet(
                avatar =  profileData.avatars?.avatar,
                bigAvatar = profileData.avatars?.bigAvatar,
                miniAvatar = profileData.avatars?.miniAvatar
            )
            )
    }

    fun mapPutEntityToPutDTO(userPutInfo: UserPutInfo):OutputDataDTO{
        return OutputDataDTO(
            name = userPutInfo.name ?: "",
            username = userPutInfo.username ?: "",
            birthday = userPutInfo.birthday ?: "",
            city = userPutInfo.city ?: "",
            vk =  userPutInfo.city ?:"",
            instagram = userPutInfo.instagram?:"",
            status = userPutInfo.status?:"",
            avatar = AvatarPutDTO(
                filename = userPutInfo.avatar?.filename ?:"",
                base64 = userPutInfo.avatar?.base64 ?:""
            )
        )


    }




}