package com.example.test_message.pro.presentation.old.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.databinding.ActivityDialogChatBinding
import com.example.test_message.pro.presentation.old.recyclerView.ChatAdapter
import com.example.test_message.pro.presentation.old.viewModels.ChatViewModel
import com.example.test_message.pro.presentation.old.viewModels.MessageApp
import com.example.test_message.pro.presentation.ViewModelFactory
import javax.inject.Inject

class DialogChatActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDialogChatBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ChatViewModel


    private val component by lazy {
        (application as MessageApp).component
    }



    private lateinit var adapterList: ChatAdapter

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