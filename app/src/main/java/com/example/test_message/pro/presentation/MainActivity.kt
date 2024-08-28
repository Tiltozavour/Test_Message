package com.example.test_message.pro.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_message.databinding.ActivityMainBinding
import com.example.test_message.pro.domain.RegistrationEntity.UserInfo
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.AuthDTO.PhoneDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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
                val intent = LogInAndRegistrationActivity
                    .newIntent(this@MainActivity, LogInAndRegistrationActivity.FRAGMENT_AUTH)
                startActivity(intent)
            }
            butRegistration.setOnClickListener {
                val intent = LogInAndRegistrationActivity
                    .newIntent(this@MainActivity, LogInAndRegistrationActivity.FRAGMENT_REGISTR)
                startActivity(intent)
            }

        }
    }



}

