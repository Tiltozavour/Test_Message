package com.example.test_message.pro.presentation.activityEntry.logIn

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.test_message.databinding.FragmentLogInBinding
import com.example.test_message.pro.domain.entity.userActivity.PhoneUserEntity
import com.example.test_message.pro.presentation.ViewModelFactory
import com.example.test_message.pro.presentation.old.viewModels.MessageApp
import javax.inject.Inject


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LogInViewModel

    private lateinit var phoneUser: PhoneUserEntity
    private var checkButton = BUTTON_DEFAULT

    private val component by lazy {
        (requireActivity().application as MessageApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LogInViewModel::class.java]
        viewModel.checkState.observe(viewLifecycleOwner) {
            it?.let {
                renderState(it)
            }
        }
        innitButton()
    }


    private fun renderState(viewState: LogInViewModel.CheckState) {
        when (viewState.isChecking) {
            true -> { with(binding) { progressBarLogin.visibility = View.VISIBLE } }
            false -> { with(binding) { progressBarLogin.visibility = View.GONE } }
        }
        when (viewState.isSuccess) {
            true -> {
                when(checkButton){
                    BUTTON_AUTH ->  innitAuthorization()
                    BUTTON_REGIST -> innitRegistration()
                }

                }
            false -> {}
            }
        when (viewState.isError) {
            true -> {}
            false -> {}
        }

    }

    private fun innitAuthorization() {
        findNavController().navigate(
            LogInFragmentDirections.actionLogInFragmentToAuthCodeFragment(phoneUser)
        )
    }

    private fun innitRegistration() {
        findNavController().navigate(
            LogInFragmentDirections.actionLogInFragmentToRegistrationFragment2(phoneUser)
        )
    }


    private fun innitButton() {
        binding.butEntry.setOnClickListener {
            when (binding.etNumberPhone.text.isNotBlank()) {
                true -> {
                    checkButton = BUTTON_AUTH
                    phoneUser = getUserNumber()
                    viewModel.checkPhoneValid(phoneUser)
                }
                false -> { makeToast() }
            }
        }
        binding.butRegistration.setOnClickListener {
            when (binding.etNumberPhone.text.isNotBlank()) {
                true -> {
                    checkButton = BUTTON_REGIST
                    phoneUser = getUserNumber()
                    viewModel.checkPhoneValid(phoneUser)
                }

                false -> { makeToast() }
            }
        }
    }

    private fun makeToast() {
        Toast.makeText(requireActivity(), toastEmptyPhone, Toast.LENGTH_SHORT).show()
    }

    private fun getUserNumber(): PhoneUserEntity {
        val full = with(binding) {
            ccp.apply {
                registerCarrierNumberEditText(etNumberPhone)
            }.fullNumberWithPlus
        }
        return PhoneUserEntity(full)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    companion object {

        private const val toastEmptyPhone =
            "Мне нужно знать кому звонить по ночам,сенпай. Введи...телефон..."

        private const val BUTTON_AUTH = 100
        private const val BUTTON_REGIST = 111
        private const val BUTTON_DEFAULT = 0

    }

}



