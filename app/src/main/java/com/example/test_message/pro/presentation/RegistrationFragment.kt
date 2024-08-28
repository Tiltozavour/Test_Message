package com.example.test_message.pro.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_message.databinding.FragmentRegistrationBinding
import com.example.test_message.pro.presentation.LogInAndRegistrationActivity.Companion.DEFAULT_PHONE
import kotlin.jvm.Throws


class RegistrationFragment : Fragment() {


    private var _binding : FragmentRegistrationBinding?=null
    private val binding:FragmentRegistrationBinding
        get() = _binding?:throw RuntimeException("Attempt to call binding methods outside the view")



    private var phone = DEFAULT_PHONE

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

        binding.edPhoneUser.text = phone.toString()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getParams(){
        val args = requireArguments()
       phone = args.getString(KEY_PHONE).toString()
    }


    companion object{

        private const val KEY_PHONE = "phone"


        fun newInstance(phone:String): RegistrationFragment {
            return RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_PHONE, phone)
                }
            }
        }


    }


}