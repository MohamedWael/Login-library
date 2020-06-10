package com.github.mohamedwael.login.passwordlogin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.config.BROADCAST_ACTION_ACTION_SUCCESS
import com.github.mohamedwael.login.databinding.PasswordLoginFragmentBinding


open class PasswordLoginFragment : BaseLoginFragment() {

    companion object {
        fun newInstance() =
            PasswordLoginFragment()
    }

    private lateinit var viewModel: PasswordLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel =
            createViewModel(PasswordLoginViewModel::class.java, PasswordLoginViewModelFactory)
        val binding = PasswordLoginFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return attachToRootView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onLoginSuccessLiveData.observe(viewLifecycleOwner, Observer {
            activity?.sendBroadcast(Intent(BROADCAST_ACTION_ACTION_SUCCESS).putExtras(it))
        })
        bindNavigationActions(view)
    }

    open fun bindNavigationActions(view: View) {
        viewModel.onVerificationLoginClick = {
            Navigation.findNavController(view)
                .navigate(R.id.action_passwordLoginFragment_to_usernameValidationFragment)
        }

        viewModel.onSignUpClick = {
            Navigation.findNavController(view)
                .navigate(R.id.action_passwordLoginFragment_to_signUpFragment)
        }

        viewModel.onForgetPasswordClick = {
            Navigation.findNavController(view)
                .navigate(R.id.action_passwordLoginFragment_to_forgetPasswordFragment)
        }
    }

}
