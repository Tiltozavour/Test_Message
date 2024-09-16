package com.example.test_message.pro.presentation.activityEntry.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test_message.databinding.FragmentRegistrationBinding
import com.example.test_message.pro.domain.entity.userActivity.UserInfoEntity
import com.example.test_message.pro.presentation.old.chatProfileActivity.ChatActivity
import com.example.test_message.pro.presentation.old.viewModels.AuthRegistViewModel
import com.example.test_message.pro.presentation.ViewModelFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


class RegistrationFragment : Fragment() {


    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    //private var phone = DEFAULT_PHONE

   // private lateinit var onShowingToast: OnShowingToastListener

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AuthRegistViewModel


    private val component by lazy {
        (requireActivity().application as MessageApp).component
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
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthRegistViewModel::class.java]
        binding.edPhoneUser.text = "phone"
        binding.ButAuthorization.setOnClickListener {
            getAuthorization()
        }
    }

    override fun onAttach(context: Context) {
        //component.inject(this)
        super.onAttach(context)
 if (context is OnShowingToastListener) {
            onShowingToast = context
        } else {
            throw RuntimeException("Activity must implement OnShowingToastListener")
        }

    }


    private fun getAuthorization() {
        with(binding) {
            if (etNickname.text.isBlank() || etName.text.isBlank()) {
                //onShowingToast.onShowingToast()
            } else {
                var check:Boolean
                lifecycleScope.launch {
                    check = initViewModel(getUserInfo())
                    when(check){
                        true ->  intentChat(getUserInfo())
                        false ->  Log.d("kdkwa", "sefes")
                    //onShowingToast.onShowingErrorToast()

                    }
                }
            }
            }
        }

    private suspend fun initViewModel(userInfo: UserInfoEntity): Boolean {
        val answer = lifecycleScope.async {
            viewModel.registration(userInfo)
        }.await()
        return answer
    }


    private fun intentChat(userInfo:UserInfoEntity) {
        val intent =
            ChatActivity.newIntent(requireActivity())
        startActivity(intent)
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
        return UserInfoEntity(phone,name, userName)
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
        fun onShowingErrorToast()
    }




}
