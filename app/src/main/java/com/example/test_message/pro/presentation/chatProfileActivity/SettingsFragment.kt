package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.databinding.FragmentSettingsBinding
import com.example.test_message.pro.presentation.viewModels.ChatViewModel
import com.example.test_message.pro.presentation.viewModels.MessageApp
import com.example.test_message.pro.presentation.viewModels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")


    private lateinit var visibility:getVisibility

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ChatViewModel


    private val component by lazy {
        (requireActivity().application as MessageApp).component
    }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if (context is SettingsFragment.getVisibility) {
            visibility = context
        } else {
            throw RuntimeException("Activity must implement Visibility")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,viewModelFactory)[ChatViewModel::class.java]
        initViewModel()
        binding.butSave.setOnClickListener {
            visibility.visibility() //возвращает кнопку на место в активити
            requireActivity().onBackPressedDispatcher.onBackPressed()

        }


    }

    private fun saveInfo(){
        viewModel.userInput.observe(viewLifecycleOwner){

        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.getProfileInfo()
        }
        viewModel.userProfile.observe(viewLifecycleOwner) {
            with(binding) {
                etCity.hint = it.city
                etTelephone.hint = it.phone
                etUserName.hint = it.username
                etVk.hint = it.vk
                etBirthday.hint = it.birthday
                etInsta.hint = it.instagram
                etName.hint = it.name
                etStatus.hint = it.status
            }
        }
    }


override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

companion object {
    fun newInstance(): SettingsFragment {
        return SettingsFragment()
    }

}
    interface getVisibility{
        fun visibility()
    }

}