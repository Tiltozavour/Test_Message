package com.example.test_message.pro.presentation.RV

import androidx.recyclerview.widget.DiffUtil
import com.example.test_message.pro.domain.entity.ChatEntity

class ChatListDiffCallback:DiffUtil.ItemCallback<ChatEntity>() {

    override fun areItemsTheSame(oldItem: ChatEntity, newItem: ChatEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatEntity, newItem: ChatEntity): Boolean {
        return oldItem == newItem
    }
}