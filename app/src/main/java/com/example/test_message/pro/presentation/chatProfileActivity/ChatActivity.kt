package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.R
import com.example.test_message.databinding.ActivityChatBinding
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.presentation.loginAndRegisActivity.LogInAndRegistrationActivity
import com.example.test_message.pro.presentation.recyclerView.ChatListRVAdapter
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
        buttonNavigation()
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
        adapterList.onChatClick = object : ChatListRVAdapter.onChatClickListener{
            override fun onChatClick(chat: ChatEntity) {
                val intent = DialogChatActivity.newIntent(this@ChatActivity)
                startActivity(intent)
            }
        }

    }

    private fun buttonNavigation(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.profileNavButton -> {
                    val intent = ProfileActivity.newIntent(this)
                    startActivity(intent)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }


    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            return intent
        }


    }


}