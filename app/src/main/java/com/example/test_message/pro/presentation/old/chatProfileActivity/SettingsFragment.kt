package com.example.test_message.pro.presentation.old.chatProfileActivity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.databinding.FragmentSettingsBinding
import com.example.test_message.pro.domain.entity.chatEntity.AvatarPut
import com.example.test_message.pro.domain.entity.chatEntity.UserPutInfo
import com.example.test_message.pro.presentation.old.viewModels.ChatViewModel
import com.example.test_message.pro.presentation.old.viewModels.MessageApp
import com.example.test_message.pro.presentation.ViewModelFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")


    private lateinit var visibility: getVisibility

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ChatViewModel


    private val component by lazy {
        (requireActivity().application as MessageApp).component
    }


    override fun onAttach(context: Context) {
       // component.inject(this)
        super.onAttach(context)
        if (context is getVisibility) {
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
            lifecycleScope.launch {
                saveInfo(getNewInfo())
            }
            visibility.visibility() //возвращает кнопку на место в активити
            requireActivity().onBackPressedDispatcher.onBackPressed()

        }


    }
    private suspend fun saveInfo(userInfo:UserPutInfo){
       lifecycleScope.async {
           viewModel.inputInfo(userInfo)
       }.await()
    }

    private fun getNewInfo():UserPutInfo{
        var name = ""
        var userName = ""
        viewModel.userProfile.observe(viewLifecycleOwner){
            name = it.name
            userName = it.username
        }

        return UserPutInfo(
            name = name,
            username = (binding.etUserName.text?:userName).toString(),
            birthday = (binding.etBirthday.text?:"10.12.2077").toString(),
            city = (binding.etCity.text?:"Eben`grad").toString(),
            vk = (binding.etVk.text?:"https://vk.com/id0").toString(),
            instagram = (binding.etVk.text?:"Not instaDiva").toString(),
            status = (binding.etStatus.text?:"Мы там дело на пол дела не да").toString(),
            avatar = AvatarPut(
                filename = "imageЗаглушка",
                base64 = "бейзЗаглушка"
            )
        )
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