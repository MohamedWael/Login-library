package com.github.mohamedwael.login.forgetpassword

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.databinding.ForgetPasswordFragmentBinding

class ForgetPasswordFragment : BaseLoginFragment() {

    companion object {
        fun newInstance() = ForgetPasswordFragment()
    }

    private lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            createViewModel(ForgetPasswordViewModel::class.java, ForgetPasswordViewModelFactory)
        val binding = ForgetPasswordFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.onCreatePasswordClick = {
            Navigation.findNavController(binding.root)
                    //TODO The below ID should be deleted
                .navigate(R.id.action_forgetPasswordFragment_to_createPasswordFragment)
        }
        return binding.root
    }
}
