package com.example.test_message.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_message.data.network.ApiFactory
import com.example.test_message.databinding.ActivityMainBinding
import com.example.test_message.domain.registrationEntity.Phone
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
       binding.butEntry.setOnClickListener {
           test()
       }
        binding.butRegistration.setOnClickListener {
            val intent = LogInAndRegistrationActivity.newIntent(this)
            startActivity(intent)
        }

    }




    private fun test() {
        CoroutineScope(Dispatchers.Main).launch {
            val phone = Phone("8479274")
            val resp = ApiFactory.apiService.aboutMe()
            Log.d("testApi", resp.toString() )
        }

    }



}