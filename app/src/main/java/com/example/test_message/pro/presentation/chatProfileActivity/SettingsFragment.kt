package com.example.test_message.pro.presentation.chatProfileActivity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_message.databinding.FragmentSettingsBinding
import com.example.test_message.pro.presentation.loginAndRegisActivity.RegistrationFragment.OnShowingToastListener


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding
            ?: throw RuntimeException("Attempt to call binding methods outside the view")

    private lateinit var onReturnView:OnReturnViewListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.butClose.setOnClickListener {
            onReturnView.onReturnView()
           requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
            if (context is OnReturnViewListener) {
               onReturnView = context
            } else {
                throw RuntimeException("Activity must implement OnShowingToastListener")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {


        fun newInstance():SettingsFragment{
            return SettingsFragment()
        }

    }

    interface OnReturnViewListener {
        fun onReturnView()
    }

}