package com.github.mohamedwael.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.databinding.SignUpFragmentBinding

class SignUpFragment : BaseLoginFragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel(SignUpViewModel::class.java, SignUpViewModelFactory)
        val binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
