package com.example.test_message.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_message.R
import com.example.test_message.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {


    private lateinit var  binding:FragmentRegistrationBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }




    companion object{

        private const val KEY_PHONE = "phone"


        fun newInstance(phone:String):RegistrationFragment{
            return RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_PHONE, phone)
                }
            }
        }


    }


}