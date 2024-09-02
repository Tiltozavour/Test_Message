package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.R
import com.example.test_message.databinding.ActivityDialogChatBinding
import com.example.test_message.pro.presentation.recyclerView.ChatAdapter
import com.example.test_message.pro.presentation.recyclerView.ChatListRVAdapter
import com.example.test_message.pro.presentation.viewModels.ChatViewModel

class DialogChatActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDialogChatBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy{
        ViewModelProvider(this)[ChatViewModel::class.java]
    }



    private lateinit var adapterList: ChatAdapter

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
        val RVListChat = binding.RVChat
        adapterList = ChatAdapter()
        RVListChat.adapter = adapterList
    }


    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DialogChatActivity::class.java)
            return intent
        }


    }
}