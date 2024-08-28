package com.example.test_message.pro.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_message.R
import com.example.test_message.databinding.ActivityLogInAndRegistrationBinding
import com.example.test_message.pro.data.network.ApiFactory
import com.example.test_message.pro.data.network.AuthDTO.PhoneDTO
import com.example.test_message.pro.domain.RegistrationEntity.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LogInAndRegistrationActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityLogInAndRegistrationBinding.inflate(layoutInflater)
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
        val screenMode = intent.getStringExtra(KEY_FRAGMENT).toString()
        val phone = "4444444"
        launchNextPage(screenMode, phone)
    }


    private fun launchNextPage(screenMode: String, phone: String) {
        val fragment = when (screenMode) {
            FRAGMENT_AUTH -> AuthCodeFragment.newInstanceAuth()
            FRAGMENT_REGISTR -> RegistrationFragment.newInstance(phone)
            else -> throw RuntimeException("Unknown screenMode $screenMode")
        }
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }


    companion object {

        const val KEY_FRAGMENT = "key_fragment"
        const val FRAGMENT_AUTH = "fragment_auth"
        const val FRAGMENT_REGISTR = "fragment_registr"
        const val KEY_PHONE = "key_phone"



        fun newIntent(context: Context, fragment: String, phone: String): Intent {
            val intent = Intent(context, LogInAndRegistrationActivity::class.java)
            intent.putExtra(KEY_FRAGMENT, fragment)
            intent.putExtra(KEY_PHONE, phone)
            return intent
        }

    }


    private fun test() {


        CoroutineScope(Dispatchers.IO).launch {

            val phoneDTO = PhoneDTO("+79219999999.")

            val userOne = UserInfo("748543", "Anna", "Tita")
            //val resp = ApiFactory.apiService.getAutorization(userOne)
            val auth = ApiFactory.apiService.sendAuthCode(phoneDTO)
            Log.d("testApi", auth.toString())
        }

    }


}