package com.example.test_message.pro.presentation.loginAndRegisActivity

import android.content.Context
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.test_message.databinding.FragmentRegistrationBinding
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.presentation.chatProfileActivity.ChatActivity
import com.example.test_message.pro.presentation.loginAndRegisActivity.LogInAndRegistrationActivity.Companion.DEFAULT_PHONE
import com.example.test_message.pro.presentation.viewModels.AuthRegistViewModel
import kotlin.concurrent.thread


class RegistrationFragment : Fragment() {


    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    private var phone = DEFAULT_PHONE

    private lateinit var onShowingToast: OnShowingToastListener

    private val viewModel by lazy {
        ViewModelProvider(this)[AuthRegistViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getParams()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getParams()
        binding.edPhoneUser.text = phone
        binding.ButAuthorization.setOnClickListener {
            getAuthorization()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnShowingToastListener) {
            onShowingToast = context
        } else {
            throw RuntimeException("Activity must implement OnShowingToastListener")
        }
    }

    private fun intentChat(userInfo:UserInfoEntity) {
    val intent =
     ChatActivity.newIntent(requireActivity(), userInfo)
     startActivity(intent)
     }


    private fun getAuthorization() {
        with(binding) {
            if (etNickname.text.isBlank() || etName.text.isBlank()) {
                onShowingToast.onShowingToast()
            } else {
                val user = getUserInfo()
                viewModel.registration(user)
                intentChat(user)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getParams() {
        val args = requireArguments()
        phone = args.getString(KEY_PHONE).toString()

    }

    private fun getUserInfo(): UserInfoEntity {
        val userName = binding.etNickname.text.toString()
        val name = binding.etName.text.toString()
        return UserInfoEntity(phone, userName, name)
    }


    companion object {

        private const val KEY_PHONE = "phone"


        fun newInstance(phone: String): RegistrationFragment {
            return RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_PHONE, phone)
                }
            }
        }


    }

    interface OnShowingToastListener {
        fun onShowingToast()
    }


}