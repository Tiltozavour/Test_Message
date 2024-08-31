package com.example.test_message.pro.presentation.loginAndRegisActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.databinding.FragmentAuthCodeBinding
import com.example.test_message.pro.presentation.viewModels.AuthRegistViewModel

class AuthCodeFragment : Fragment() {


    private var _binding: FragmentAuthCodeBinding? = null
    private val binding: FragmentAuthCodeBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    private var phone = DEFAULT_PHONE

    private val viewModel by lazy {
        ViewModelProvider(this)[AuthRegistViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        phone = getParams()

        if (initViewModel(phone, TEST_CODE))
        {
            //intentChat()
        }
        else
        {
            RegistrationFragment.newInstance(phone)
        }

    }

    private fun initViewModel(phone: String, code: String): Boolean {
        return viewModel.checkAuthCode(phone, code)
    }

    //private fun intentChat() {
        //val intent = ChatActivity.newIntent(requireActivity(), UserInfoEntity("w", "2", "w"))
       // startActivity(intent)
    //}

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


    companion object {

        const val KEY_FRAGMENT = "key_fragment"
        const val FRAGMENT_AUTH = "fragment_auth"
        private const val KEY_PHONE = "phone"
        private const val DEFAULT_PHONE = ""
        private const val TEST_CODE = "133337"


        fun newInstanceAuth(phone: String): AuthCodeFragment {
            return AuthCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FRAGMENT, FRAGMENT_AUTH)
                    putString(KEY_PHONE, phone)
                }
            }
        }


    }

}

/*  private val repository = AppRepositoryImpl
   fun test(){
       CoroutineScope(Dispatchers.Main).launch {
           val phone = PhoneUserEntity("+79996116565")
           repository.sendAuthCodeUseCase(phone)
       }
   }*/