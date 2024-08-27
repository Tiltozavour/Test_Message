package com.example.test_message.pro

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_message.databinding.ActivityMainBinding
import com.example.test_message.pro.domain.RegistrationEntity.UserInfo
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.ApiFactory.apiService
import com.example.test_message.pro.domain.AuthEntity.Phone
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

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


        CoroutineScope(Dispatchers.IO).launch {

            val phone = Phone("+79996996969")

           val userOne = UserInfo("748543", "Anna", "Tita")
          //val resp = ApiFactory.apiService.getAutorization(userOne)
            val auth = ApiFactory.apiService.sendAuthCode(phone)
          Log.d("testApi", auth.toString() )
        }

    }



}

