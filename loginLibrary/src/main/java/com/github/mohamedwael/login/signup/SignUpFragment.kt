package com.github.mohamedwael.login.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.github.mohamedwael.login.base.BaseLoginFragment
import com.github.mohamedwael.login.config.BROADCAST_ACTION_SUCCESS
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
        observeHideKeyboardEvent(viewModel)
        val binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onSignUpSuccessLiveData.observe(viewLifecycleOwner, Observer {
            activity?.sendBroadcast(Intent(BROADCAST_ACTION_SUCCESS).putExtras(it))
        })
    }
}
