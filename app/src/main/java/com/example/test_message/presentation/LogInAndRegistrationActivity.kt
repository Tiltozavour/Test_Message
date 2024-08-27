package com.example.test_message.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_message.R
import com.example.test_message.databinding.ActivityLogInAndRegistrationBinding


private lateinit var binding:ActivityLogInAndRegistrationBinding



class LogInAndRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInAndRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.ActivityRegistrationAndLogIn) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun launchRegistrationPage(){
        supportFragmentManager.beginTransaction()
            .add(R.id.main, RegistrationFragment.newInstance("54544"))
            .addToBackStack(null)
            .commit()

    }


    companion object{

        fun newIntent(context: Context):Intent{
            val intent = Intent(context,LogInAndRegistrationActivity::class.java)
            return intent
        }

    }



}