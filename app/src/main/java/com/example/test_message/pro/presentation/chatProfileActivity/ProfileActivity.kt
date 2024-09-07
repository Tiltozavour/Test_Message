package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.R
import com.example.test_message.databinding.ActivityProfileBinding
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.presentation.viewModels.ChatViewModel
import com.example.test_message.pro.presentation.viewModels.MessageApp
import com.example.test_message.pro.presentation.viewModels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileActivity : AppCompatActivity(),
 SettingsFragment.getVisibility{

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel:ChatViewModel


    private val component by lazy {
        (application as MessageApp).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[ChatViewModel::class.java]
        getProfileFromBase()
        buttonNavigation()
        binding.dotSetting.setOnClickListener {
            launchSettingsPage()
            binding.dotSetting.visibility = View.GONE
        }
    }


    private fun buttonNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.chatNavButton -> {
                    val intent = ChatActivity.newIntent(this)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    private fun getProfileFromBase() {
        lifecycleScope.launch {
            viewModel.getProfileInfo()
            setInfo()
        }

    }

    private fun setInfo() {
        viewModel.userProfile.observe(this) {
            with(binding) {
                tvName.text = it.name
                tvCity.text = it.city
                tvNickname.text = it.username
                tvBirthday.text = it.birthday
                tvTelephone.text = plusSigh + it.phone
                tvAboutMe.text = it.status
            }
        }
    }


    private fun launchSettingsPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentChatContainer, SettingsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun visibility() {
       binding.dotSetting.visibility = View.VISIBLE
    }

    companion object {

        private const val plusSigh = "+"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            return intent
        }

    }

}