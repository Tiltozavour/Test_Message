package com.example.test_message.pro.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.test_message.databinding.ActivityChatBinding
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.presentation.RV.ChatListRVAdapter
import com.example.test_message.pro.presentation.viewModels.ChatViewModel

class ChatActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy{
        ViewModelProvider(this)[ChatViewModel::class.java]
    }

    private lateinit var adapterList: ChatListRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModelInnit()
        setupRV()
    }

    private fun viewModelInnit(){
        viewModel.chatList.observe(this){
            adapterList.submitList(it)
        }

    }


    private fun setupRV() {
        val RVList = binding.RVList
        adapterList = ChatListRVAdapter()
        RVList.adapter = adapterList

    }


    companion object {

        private const val KEY_INFO_USER = "user_info"

        fun newIntent(context: Context /*userInfo: UserInfoEntity*/): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            //intent.putExtra(KEY_INFO_USER, userInfo)
            return intent
        }


    }


}