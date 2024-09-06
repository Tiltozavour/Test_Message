package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.databinding.FragmentSettingsBinding
import com.example.test_message.pro.domain.entity.chatEntity.AvatarPut
import com.example.test_message.pro.domain.entity.chatEntity.UserProfile
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo
import com.example.test_message.pro.presentation.loginAndRegisActivity.AuthCodeFragment
import com.example.test_message.pro.presentation.loginAndRegisActivity.RegistrationFragment.OnShowingToastListener
import com.example.test_message.pro.presentation.viewModels.ChatViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")


    private lateinit var visibility:getVisibility

    private val viewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun onAttach(context: Context) {
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
        initViewModel()
        binding.butSave.setOnClickListener {
            lifecycleScope.launch {
                if (saveInfo()){
                    visibility.visibility()
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }

        }
    }


    private suspend fun saveInfo():Boolean{
        val user = UserPutInfo(
            name = binding.etName.text.toString(),
            username = binding.etUserName.text.toString(),
            birthday = binding.etBirthday.text.toString(),
            city = binding.etCity.text.toString(),
            vk = binding.etVk.text.toString(),
            instagram = binding.etInsta.text.toString(),
            status = binding.etStatus.text.toString(),
            avatar = AvatarPut(
                filename = "userAvatar01",
                base64 = "заглушка"
            )
        )
        val answer = lifecycleScope.async {
             viewModel.inputInfo(user)
         }.await()

        return answer
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