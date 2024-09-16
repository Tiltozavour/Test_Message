package com.example.test_message.pro.presentation.old.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_message.databinding.ChatItemBinding
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity

class ChatListRVAdapter :
    ListAdapter<ChatEntity, ChatListRVAdapter.chatViewHolder>(ChatListDiffCallback()) {

        var onChatClick: onChatClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return chatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: chatViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvNameItem.text = item.name
            tvTextItem.text = item.text
            root.setOnClickListener {
                onChatClick?.onChatClick(item)
            }
        }

    }

    class chatViewHolder(val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root)


    interface onChatClickListener{
        fun onChatClick(chat: ChatEntity)

    }


}