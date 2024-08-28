package com.example.test_message.pro.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_message.databinding.FragmentAuthCodeBinding

class AuthCodeFragment : Fragment() {


    private lateinit var binding: FragmentAuthCodeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthCodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {


        const val KEY_FRAGMENT = "key_fragment"
        const val FRAGMENT_AUTH = "fragment_auth"
        private const val KEY_PHONE = "phone"


        fun newInstanceAuth(phone:String): AuthCodeFragment {
            return AuthCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FRAGMENT, FRAGMENT_AUTH)
                    putString(KEY_PHONE, phone)
                }
            }
        }


    }


}