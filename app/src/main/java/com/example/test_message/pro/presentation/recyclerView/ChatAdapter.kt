package com.example.test_message.pro.presentation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_message.R
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity

class ChatAdapter:ListAdapter<ChatEntity, ChatAdapter.chatViewHolder>(ChatDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ANSWER -> R.layout.item_answer
            VIEW_TYPE_ASK ->R.layout.item_ask
            else -> throw  RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return chatViewHolder(view)
    }

    override fun onBindViewHolder(holder: chatViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvText.text = item.text
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.userOrAsk){
            VIEW_TYPE_ANSWER
        } else {
            VIEW_TYPE_ASK
        }
    }


    class chatViewHolder(val view: View ) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tvNameItem)
        val tvText = view.findViewById<TextView>(R.id.tvTextItem)


    }

    companion object {
        const val VIEW_TYPE_ASK = 69
        const val VIEW_TYPE_ANSWER = 58
    }

}

