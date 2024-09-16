package com.example.test_message.pro.presentation.old.loginAndRegisActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_message.R
import com.example.test_message.databinding.ActivityLogInAndRegistrationBinding
import com.example.test_message.pro.presentation.activityEntry.authorization.AuthCodeFragment


class LogInAndRegistrationActivity : AppCompatActivity() {

/*

    private val binding by lazy {
        ActivityLogInAndRegistrationBinding.inflate(layoutInflater)
    }

    private var screenMode = DEFAULT_SCREEN
    private var phone = DEFAULT_PHONE



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        screenMode = intent.getStringExtra(KEY_FRAGMENT).toString()
        phone = intent.getStringExtra(KEY_PHONE).toString()
        launchNextPage(screenMode, phone)
    }


    private fun launchNextPage(screenMode: String, phone: String) {
        val fragment = when (screenMode) {
            FRAGMENT_AUTH -> AuthCodeFragment.newInstanceAuth(phone)
            FRAGMENT_REGISTR -> RegistrationFragment.newInstance(phone)
            else -> throw RuntimeException("Unknown screenMode $screenMode")
        }
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }

 */
/*   override fun onShowingErrorToast() {
        Toast.makeText(this, error_toast_text,Toast.LENGTH_LONG).show()
    }

    override fun onShowingToast() {
        Toast.makeText(this, messageToast,Toast.LENGTH_LONG).show()
    }*//*


 */
/*   override fun onShowingToastAuth() {
       Toast.makeText(this, toast_text,Toast.LENGTH_LONG).show()
    }*//*


    companion object {

        const val KEY_FRAGMENT = "key_fragment"
        const val FRAGMENT_AUTH = "fragment_auth"
        const val FRAGMENT_REGISTR = "fragment_registr"
        const val KEY_PHONE = "key_phone"
        const val DEFAULT_PHONE = ""
        const val DEFAULT_SCREEN = ""
        private const val messageToast = "Какие-то поля всё ещё пусты...прямо как моё сердечко♥"
        private const val toast_text = "Семпай, это не тот...код"
        private const val error_toast_text = "Что-то не так с регистрацией"



        fun newIntent(context: Context, fragment: String, phone: String): Intent {
            val intent = Intent(context, LogInAndRegistrationActivity::class.java)
            intent.putExtra(KEY_FRAGMENT, fragment)
            intent.putExtra(KEY_PHONE, phone)
            return intent
        }

    }

*/




}