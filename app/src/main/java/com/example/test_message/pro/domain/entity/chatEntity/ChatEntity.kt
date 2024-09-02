package com.example.test_message.pro.domain.entity.chatEntity

data class ChatEntity(

    var id:Int = UNDEFINED_ID,
    val name:String,
    val text:String,

    ) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}

