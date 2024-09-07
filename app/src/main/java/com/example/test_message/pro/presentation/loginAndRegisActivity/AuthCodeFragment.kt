package com.example.test_message.pro.presentation.loginAndRegisActivity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.databinding.FragmentAuthCodeBinding
import com.example.test_message.pro.presentation.chatProfileActivity.ChatActivity
import com.example.test_message.pro.presentation.viewModels.AuthRegistViewModel
import com.example.test_message.pro.presentation.viewModels.MessageApp
import com.example.test_message.pro.presentation.viewModels.ViewModelFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthCodeFragment : Fragment() {


    private var _binding: FragmentAuthCodeBinding? = null
    private val binding: FragmentAuthCodeBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    private lateinit var onShowingToast: OnShowingToastListener

    private var phone = DEFAULT_PHONE

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel:AuthRegistViewModel


    private val component by lazy {
        (requireActivity().application as MessageApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthRegistViewModel::class.java]
        phone = getParams()
        binding.ButAuthorization.setOnClickListener {
            if(checkCode()){
                var check:Boolean
                lifecycleScope.launch {
                    check =   initViewModel(phone, TEST_CODE)
                    when (check) {
                        true ->  intentChat()
                        false -> intentRegistration()
                    }
                }
            }

        }
    }

    private suspend fun initViewModel(phone: String, code: String): Boolean {
          val answer = lifecycleScope.async {
              viewModel.checkAuthCode(phone, code)
          }.await()
        return answer
    }

    private fun checkCode():Boolean {
        val text = binding.etCode.text
        if (text.isBlank() || text.length < CODE_VALUE) {
            onShowingToast.onShowingToastAuth()
            return false
        }
            return true
    }


    private fun intentRegistration(){
        val intent = LogInAndRegistrationActivity.newIntent(
            requireActivity(),
            LogInAndRegistrationActivity.FRAGMENT_REGISTR,
            phone
        )
        startActivity(intent)
    }


    private fun intentChat() {
        val intent = ChatActivity.newIntent(requireActivity())
        startActivity(intent)
    }

    private fun getParams(): String {
        val args = requireArguments()
        args.getString(KEY_PHONE)?.let {
            phone = it
        }
        return phone
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if (context is OnShowingToastListener) {
            onShowingToast = context
        } else {
            throw RuntimeException("Activity must implement OnShowingToastListener")
        }
    }


    companion object {

        const val KEY_FRAGMENT = "key_fragment"
        const val FRAGMENT_AUTH = "fragment_auth"
        private const val KEY_PHONE = "phone"
        private const val DEFAULT_PHONE = ""
        private const val TEST_CODE = "133337"
        private const val CODE_VALUE = 6



        fun newInstanceAuth(phone: String): AuthCodeFragment {
            return AuthCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FRAGMENT, FRAGMENT_AUTH)
                    putString(KEY_PHONE, phone)
                }
            }
        }


    }
    interface OnShowingToastListener {
        fun onShowingToastAuth()
    }

}

