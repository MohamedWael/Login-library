package com.github.mohamedwael.login.verificationcodelogin.usernamevalidation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.databinding.UsernameValidationFragmentBinding
import com.github.mohamedwael.login.verificationcodelogin.verification.KEY_USER_NAME

open class UsernameValidationFragment : BaseLoginFragment() {

    companion object {
        fun newInstance() = UsernameValidationFragment()
    }

    private lateinit var viewModel: UsernameValidationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel(
            UsernameValidationViewModel::class.java,
            UsernameValidationViewModelFactory
        )
        observeHideKeyboardEvent(viewModel)
        val binding = UsernameValidationFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.onVerificationCodeSent = { username ->
            Navigation.findNavController(binding.root).navigate(
                R.id.action_usernameValidationFragment_to_verificationLoginFragment,
                Bundle().apply {
                    putString(KEY_USER_NAME, username)
                })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindNavigationActions(view)
    }

    open fun bindNavigationActions(view: View) {
        viewModel.onSignUpClick = {
            Navigation.findNavController(view)
                .navigate(R.id.action_usernameValidationFragment_to_signUpFragment)
        }

        viewModel.onForgetPasswordClick = {
            Navigation.findNavController(view)
                .navigate(R.id.action_usernameValidationFragment_to_forgetPasswordFragment)
        }
    }

}
