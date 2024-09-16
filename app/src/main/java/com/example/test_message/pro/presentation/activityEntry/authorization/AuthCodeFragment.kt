package com.example.test_message.pro.presentation.activityEntry.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.test_message.databinding.FragmentAuthCodeBinding
import com.example.test_message.pro.domain.entity.userActivity.PhoneCode
import com.example.test_message.pro.presentation.ViewModelFactory
import com.example.test_message.pro.presentation.old.chatProfileActivity.ChatActivity
import com.example.test_message.pro.presentation.old.viewModels.MessageApp
import javax.inject.Inject

class AuthCodeFragment : Fragment() {

    private var _binding: FragmentAuthCodeBinding? = null
    private val binding: FragmentAuthCodeBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    private val args: AuthCodeFragmentArgs by navArgs()
    private lateinit var phone: PhoneCode

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AuthorizationViewModel

    private val component by lazy {
        (requireActivity().application as MessageApp).component
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAuthCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthorizationViewModel::class.java]
        viewModel.checkState.observe(viewLifecycleOwner) {
            it?.let {
                renderState(it)
            }
        }
        phone = PhoneCode(args.phoneUser.phone, TEST_CODE)
        binding.butAuthorization.setOnClickListener {
            viewModel.checkPhoneWithCode(phone)
        }
    }

    private fun renderState(viewState: AuthorizationViewModel.CheckState) {
        when (viewState.waitingCode) {
            true -> {}
            false -> {}
        }
        when (viewState.isChecking) {
            true -> {
                with(binding){
                    progressBarLoading.visibility = View.VISIBLE
                    butAuthorization.isEnabled = false
                    etCode.isEnabled = false
                }
            }
            false -> {
                with(binding){
                    progressBarLoading.visibility = View.GONE
                    butAuthorization.isEnabled = true
                    etCode.isEnabled = true
                }
            }
        }
        when (viewState.isSuccess) {
            true -> {
                innitChat()
            }
            false -> {}
        }
        when (viewState.isError) {
            true -> {
                innitRegistration()
            }
            false -> {}
        }
    }

    private fun innitChat(){

    }
    private fun innitRegistration(){

    }


    /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         viewModel = ViewModelProvider(this, viewModelFactory)[AuthorizationViewModel::class.java]

         binding.ButAuthorization.setOnClickListener {
          *//*   if (checkCode()) {
                    var check: Boolean
                    lifecycleScope.launch {
                        //check = initViewModel(phone, TEST_CODE)
                        when (check) {
                            true -> intentChat()
                            false -> intentRegistration()
                        }
                    }
                }*//*

            }
        }*/


    private fun checkCode(): Boolean {
        val text = binding.etCode.text
        if (text.isBlank() || text.length < CODE_VALUE) {
            Toast.makeText(requireActivity(), Toast_empty_code, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }




    private fun intentChat() {
        val intent = ChatActivity.newIntent(requireActivity())
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    companion object {
        private const val TEST_CODE = "133337"
        private const val Toast_empty_code = "Я жду правильный код, душа моя"
        private const val CODE_VALUE = 6


    }

}

