package com.example.test_message.pro.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.checkDTO.CheckResponseDTO
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import kotlinx.coroutines.coroutineScope
import kotlin.random.Random


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()

    private var tokenDTO: String = ""
    private var refreshToken: String = ""


    // Проверка телефона
    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity): Boolean {
        val resp = apiService.sendAuthCode(mapper.mapEntityToDTO(phone))
        return when (resp.isSuccessful) {
            true -> {
                Log.d("testApi", "удачный чек телефона")
                true
            }

            false -> {
                Log.d("testApi", "неудачный чек телефона")
                false
            }
        }
    }

    override suspend fun checkAuthCodeUseCase(phoneCode: PhoneCode): Boolean {
        val resp = apiService.checkAuthCode(mapper.mapEntityToCodeDTO(phoneCode))
        return when (resp.isSuccessful && resp.body()?.isUserExists == true) {
            true -> {
                tokenDTO = resp.body()?.accessToken ?: throw RuntimeException("Token does`t have exist")
                refreshToken = resp.body()?.refreshToken ?: throw RuntimeException("RefreshToken does`t have exist")
               /* val exists = resp.body()?.isUserExists
                val id = resp.body()?.userId
                CheckResponseDTO(tokenDTO, refreshToken, exists, id)*/
                Log.d(
                    "testApi",
                    "Пользователь существует, данные: токен = ${tokenDTO}, refresh = ${refreshToken}, exist = ${resp.body()?.isUserExists}, id = ${resp.body()?.userId}"
                )
                true
            }
            false -> {
                Log.d("testApi", "Неудачный чек аунтификации")
                false
            }
        }
    }

    override suspend fun registrationUseCase(userInfo: UserInfoEntity): Boolean {
        val map = mapper.mapEntityToUserInfoDTO(userInfo)
        val resp = apiService.getRegistration(map)
        Log.d("testApi", " попытка регистрации -> ${resp.code()} ")
        val detail = resp.body()?.error?.detail
        if (detail != null) {
            for( i in detail){
                Log.d("testApi", "ошибка -> ${i.loc} , msg ${i.message}, type ${i.type}")
            }

        }

        return when (resp.isSuccessful) {
            true -> {
                tokenDTO = resp.body()?.accessToken
                    ?: throw RuntimeException("Token does`t have exist")
                refreshToken = resp.body()?.refreshToken
                    ?: throw RuntimeException("RefreshToken does`t have exist")
                Log.d("testApi", "Успешная регистрация  id ${resp.body()?.userId}")
                true
            }
            false -> {
                val exist = checkAuthCodeUseCase(PhoneCode(userInfo.phone, "133337"))
                if (exist) {
                    Log.d(
                        "testApi",
                        "Неудачная поптыка регистрации - ${resp.code()}, пользователь уже существует"
                    )
                } else {
                    Log.d(
                        "testApi",
                        "Неудачная поптыка регистрации - ${resp.code()}, что-то другое"
                    )
                }
                false
            }
        }
    }


// Chat
val cardItemLD = MutableLiveData<List<ChatEntity>>()
val cards = sortedSetOf<ChatEntity>({ o1, o2 -> o1.id.compareTo(o2.id) })
private var autoIncrementId = 0

init {
    for (i in 0 until 20) {
        val item = ChatEntity(i, "Name ${i}", "НЕТ ТЫ!", Random.nextBoolean())
        addList(item)
    }
}

override fun getListChatUseCase(): LiveData<List<ChatEntity>> {
    return cardItemLD
}

private fun addList(chatItem: ChatEntity) {
    if (chatItem.id == ChatEntity.UNDEFINED_ID) {
        chatItem.id = autoIncrementId++
    }
    cards.add(chatItem)
    updateList()
}

private fun updateList() {
    cardItemLD.value = cards.toList()
}


//Profile
override suspend fun getProfileInfoUseCase(): UserProfile {
    val token = "Bearer $tokenDTO"
    val responseUserInfo = apiService.getInfoUser(token)
    if (responseUserInfo.isSuccessful) {
        val userInfo = responseUserInfo.body()!!.profileData
        val avatarsDTO = responseUserInfo.body()!!.profileData.avatars
        Log.d(
            "testApi",
            " успешно же,  id ${responseUserInfo.body()?.profileData?.id} name: ${responseUserInfo.body()?.profileData?.name}"
        )
        return mapper.profileDTOToEntity(userInfo)

    } else {
        Log.d("testApi", "Неуспешный запрос профиля  ${responseUserInfo.code()}")
    }
    Log.d("testApi", "Неуспешный запрос профиля, что -то другое ${responseUserInfo.code()}")
    throw RuntimeException("Ошибка получение данных")
}


override suspend fun saveInfoUserUseCase(userPutInfo: UserPutInfo) {
    val token = "Bearer $tokenDTO"
    val mapInfo = mapper.mapPutEntityToPutDTO(userPutInfo)
    val responseUserInfo = apiService.putInfoUser(token, mapInfo)
    if (responseUserInfo.isSuccessful) {
        Log.d(
            "testApi",
            " успешно же,  id ${responseUserInfo.body()?.avatar} }"
        )

    }
}
}


private fun refreshToken() {}
