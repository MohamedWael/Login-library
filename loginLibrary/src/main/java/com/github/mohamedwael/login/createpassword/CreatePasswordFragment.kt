package com.github.mohamedwael.login.createpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.blogspot.mowael.baselibrary.fragments.BaseFragment
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.databinding.CreatePasswordFragmentBinding


class CreatePasswordFragment : BaseLoginFragment() {

    companion object {
        fun newInstance() =
            CreatePasswordFragment()
    }

    private lateinit var viewModel: CreatePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel =
            createViewModel(CreatePasswordViewModel::class.java, CreatePasswordViewModelFactory)
        observeHideKeyboardEvent(viewModel)
        val binding = CreatePasswordFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.password.observe(viewLifecycleOwner, Observer { _ ->
            viewModel.validatePassword()
        })
        viewModel.confirmPassword.observe(viewLifecycleOwner, Observer { _ ->
            viewModel.compareConfirmPasswordMatch()
        })

        return attachToRootView(binding.root)
    }


}
