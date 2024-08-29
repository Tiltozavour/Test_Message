package com.example.test_message.pro.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.databinding.ActivityMainBinding
import com.example.test_message.pro.data.AppRepositoryImpl
import com.example.test_message.pro.domain.PhoneUserEntity
import com.example.test_message.pro.domain.UserInfoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
                if (checkEmpty()) {
                    if (initViewModel()) {
                        intentAuth()
                    }
                   intentRegistration()
                }
            }
            butRegistration.setOnClickListener {
                if (checkEmpty()) {
                   intentRegistration()
                }
            }
        }
    }

    private fun initViewModel():Boolean{
      return viewModel.authorization(getFulNumberPhone())
    }

    private fun intentAuth(){
        val intent = LogInAndRegistrationActivity
            .newIntent(
                this@MainActivity,
                LogInAndRegistrationActivity.FRAGMENT_AUTH,
                getFulNumberPhone()
            )
        startActivity(intent)
    }

    private fun intentRegistration(){
        val intent = LogInAndRegistrationActivity
            .newIntent(
                this@MainActivity,
                LogInAndRegistrationActivity.FRAGMENT_REGISTR,
                getFulNumberPhone()
            )
        startActivity(intent)
    }

    private fun getFulNumberPhone():String{
        val ccp =  binding.ccp
        val numPhone = binding.etNumberPhone
        ccp.registerCarrierNumberEditText(numPhone)
        return ccp.fullNumberWithPlus

    }

    private fun checkEmpty():Boolean {
        if(binding.etNumberPhone.text.isBlank()){
            Toast.makeText(this@MainActivity, textToast, Toast.LENGTH_LONG).show()
            return false
        } else {
            return true
        }

    }

    companion object {

        private const val textToast = "Мне нужно знать кому звонить по ночам,сенпай. Введи...телефон..."

    }

}

/*  private val repository = AppRepositoryImpl
   fun test(){
       CoroutineScope(Dispatchers.Main).launch {
           val phone = PhoneUserEntity("+79996116565")
           repository.sendAuthCodeUseCase(phone)
       }
   }*/

