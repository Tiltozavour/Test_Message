package com.example.test_message.pro.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.checkDTO.CheckResponseDTO
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.data.network.profileDTO.AvatarsDTO
import com.example.test_message.pro.data.network.profileDTO.ProfileDataDTO
import com.example.test_message.pro.data.network.registrDTO.RegistrResponse
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.chatEntity.Avatars
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import kotlinx.coroutines.coroutineScope


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()

    private var tokenDTO: String = ""


    // AuthRegist
    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity): Boolean = coroutineScope {
        val resp = apiService.sendAuthCode(mapper.mapEntityToDTO(phone)) //отсылаем телефон
        val code = resp.code()  //получаем ответ сервера
        var answer = false
        when (code) {
            201 -> {
                answer = true
                Log.d("testApi", "удачный чек телефона")
            }

            422 -> {
                answer = false
                TODO("обработка ошибки")
                Log.d("testApi", "неудачный чек телефона")
            }
        }
        answer
    }

    override suspend fun checkAuthCodeUseCase(phoneCode: PhoneCode): Boolean {
        val resp = apiService.checkAuthCode(mapper.mapEntityToCodeDTO(phoneCode))
        return when (resp.isSuccessful && resp.body()?.isUserExists == true) {
            true -> {
                val token = resp.body()?.accessToken
                val refresh = resp.body()?.refreshToken
                val exists = resp.body()?.isUserExists
                val id = resp.body()?.userId
                if (token != null) {
                    tokenDTO = token
                }
                CheckResponseDTO(token, refresh, exists, id)
                Log.d(
                    "testApi",
                    "ха ха ура! " +
                            " токен = ${token}, refresh = ${refresh}, exist = ${exists}, id = ${id}"
                )
                true
            }

            false -> {
                Log.d("testApi", "неудачный чек аунтификации")
                false
            }
        }
    }

    override suspend fun registrationUseCase(userInfo: UserInfoEntity) {
        val resp = apiService.getRegistration(mapper.mapEntityToUserInfoDTO(userInfo))
        if (resp.isSuccessful) {
            val token = resp.body()?.accessToken
            val refresh = resp.body()?.refreshToken
            val id = resp.body()?.userId
            RegistrResponse(token, refresh, id)
            if (token != null) {
                tokenDTO = token
            }
            Log.d("testApi", "token ${token.toString()}, refresh ${refresh.toString()}, id =${id} ")
        } else {
            val fail = resp.errorBody()
            Log.d("testApi", "Неудачная поптыка регистрации - ${fail}")
            TODO("обработка  3")
        }
    }

    // Chat
    val cardItemLD = MutableLiveData<List<ChatEntity>>()
    val cards = sortedSetOf<ChatEntity>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0

    init {
        for (i in 0 until 20) {
            val item = ChatEntity(i, "Name ${i}", "Привет!")
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
    override suspend fun getProfileInfoUseCase():UserProfile {
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
            Log.d("testApi", "неуспешный запрос профиля ${responseUserInfo.code()}")
        }
        Log.d("testApi", "Давай по ново все хуня ${responseUserInfo.code()}")
        return UserProfile(
            null.toString(),
            null.toString(),
            null.toString(),
            null.toString(),
            null.toString(),
            null.toString(),
            null.toString(),
            null.toString(),
            0,
            null.toString(),
            false,
            null.toString(),
            null.toString(),
            0,
            null.toString()
        )
    }

}

