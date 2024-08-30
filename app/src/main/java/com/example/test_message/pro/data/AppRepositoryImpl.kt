package com.example.test_message.pro.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.mapper.AppMapper
import com.example.test_message.pro.domain.AppRepository
import com.example.test_message.pro.domain.entity.ChatEntity
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity


object AppRepositoryImpl : AppRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = AppMapper()


    override suspend fun sendAuthCodeUseCase(phone: PhoneUserEntity): Boolean {
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
                Log.d("testApi", "неудачный чек телефона")
            }
        }
        return answer
    }

    override suspend fun checkAuthCodeUseCase(phoneCode: PhoneCode): Boolean {
        val resp = apiService.checkAuthCode(mapper.mapEntityToCodeDTO(phoneCode))
        val answer: Boolean
        if (resp.isSuccessful) {
            Log.d("testApi", "ха ха ура!${resp.body()!!.accessToken}")
            if (resp.body()?.isUserExists == true) {
                answer = true
            } else {
                answer = false
            }
        }
        else answer = false
        Log.d("testApi", "неудачный чек аунтификации")
        return answer
    }

    override suspend fun registrationUseCase(userInfo: UserInfoEntity) {
        val resp = apiService.getRegistration(mapper.mapEntityToUserInfoDTO(userInfo))
        if (resp.isSuccessful) {
            val token = resp.body()?.accessToken
            val refresh = resp.body()?.refreshToken
            Log.d("testApi", "${token.toString()},${refresh.toString()}")
        } else {
            val da = resp.code()
            Log.d("testApi", "Неудачная поптыка регистрации ${da}")
        }
    }


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

}

