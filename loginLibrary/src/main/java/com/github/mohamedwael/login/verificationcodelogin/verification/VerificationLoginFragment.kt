package com.github.mohamedwael.login.verificationcodelogin.verification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.databinding.VerificationLoginFragmentBinding

class VerificationLoginFragment : BaseLoginFragment() {

    companion object {
        fun newInstance(username: String?) = VerificationLoginFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_USER_NAME, username)
            }
        }
    }

    private lateinit var viewModel: VerificationLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel(
            VerificationLoginViewModel::class.java,
            VerificationLoginViewModelFactory
        )
        observeHideKeyboardEvent(viewModel)
        viewModel.username = arguments?.getString(KEY_USER_NAME)
        val binding = VerificationLoginFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startTimer()
    }
}
