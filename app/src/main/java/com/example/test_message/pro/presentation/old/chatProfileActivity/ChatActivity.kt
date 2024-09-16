package com.example.test_message.pro.presentation.old.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.R
import com.example.test_message.databinding.ActivityChatBinding
import com.example.test_message.pro.domain.entity.chatEntity.ChatEntity
import com.example.test_message.pro.presentation.old.ProfileActivity.ProfileActivity
import com.example.test_message.pro.presentation.old.recyclerView.ChatListRVAdapter
import com.example.test_message.pro.presentation.old.viewModels.ChatViewModel
import com.example.test_message.pro.presentation.old.viewModels.MessageApp
import com.example.test_message.pro.presentation.ViewModelFactory
import javax.inject.Inject

class ChatActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ChatViewModel


    private val component by lazy {
        (application as MessageApp).component
    }


    private lateinit var adapterList: ChatListRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        //component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this,viewModelFactory)[ChatViewModel::class.java]
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