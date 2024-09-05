package com.example.test_message.pro.presentation.loginAndRegisActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.databinding.ActivityMainBinding
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.presentation.chatProfileActivity.ChatActivity
import com.example.test_message.pro.presentation.viewModels.AuthRegistViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[AuthRegistViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        launchNextPage()
    }


    private fun launchNextPage() {
        with(binding) {
            butEntry.setOnClickListener {
                if (!checkBlank()) {
                    lifecycleScope.launch {
                        initViewModel().apply {
                            intentAuth()
                        }
                    }
                }
            }
            butRegistration.setOnClickListener {
            if (!checkBlank()) {
                intentRegistration()
                }
            }
        }
    }

    private fun checkBlank():Boolean{
       if (binding.etNumberPhone.text.isBlank()) {
           Toast.makeText(this, textToast, Toast.LENGTH_LONG).show()
           return true
       }
        else return false
    }

     private suspend fun initViewModel(): Boolean {
        return viewModel.authorization(getFulNumberPhone())
    }

    private fun intentAuth() {
        val intent = LogInAndRegistrationActivity.newIntent(
            this,
            LogInAndRegistrationActivity.FRAGMENT_AUTH,
            getFulNumberPhone()
        )
        startActivity(intent)
    }

    private fun intentRegistration() {
        val intent = LogInAndRegistrationActivity.newIntent(
            this,
            LogInAndRegistrationActivity.FRAGMENT_REGISTR,
            getFulNumberPhone()
        )
        startActivity(intent)
    }

    private fun getFulNumberPhone(): String {
        val ccp = binding.ccp
        val numPhone = binding.etNumberPhone
        ccp.registerCarrierNumberEditText(numPhone)
        return ccp.fullNumberWithPlus

    }


    companion object {

        private const val textToast =
            "Мне нужно знать кому звонить по ночам,сенпай. Введи...телефон..."


    }

}



