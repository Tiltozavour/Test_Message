package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.R
import com.example.test_message.databinding.ActivityProfileBinding
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.presentation.viewModels.ChatViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
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
        buttonNavigation()
        getProfile()
    }

    private fun buttonNavigation() {
        val userInfo = UserInfoEntity("42342", "Tita", "Tutazavr")
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.chatNavButton -> {
                    val intent = ChatActivity.newIntent(this, userInfo)
                    startActivity(intent)
                    false
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun getProfile(){
        lifecycleScope.launch {
            viewModel.getProfileInfo()
            setInfo()
        }
    }

    private fun setInfo(){
        viewModel.userProfile.observe(this){
            with(binding){
                tvName.text = it.name
                tvCity.text = it.city
                tvNickname.text = it.username
                tvBirthday.text = it.birthday
                tvTelephone.text = "+" + it.phone
                tvAboutMe.text = it.status
            }
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            return intent
        }

    }

}