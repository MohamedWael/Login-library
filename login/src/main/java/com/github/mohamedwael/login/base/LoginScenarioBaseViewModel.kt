package com.github.mohamedwael.login.base

import android.view.View
import androidx.databinding.ObservableInt
import com.github.mohamedwael.login.config.InputValidationProvider
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordViewModelFactory
import com.github.mohamedwael.login.signup.SignUpViewModelFactory

abstract class LoginScenarioBaseViewModel(inputValidationProvider: InputValidationProvider) :
    UsernameBaseViewModel(inputValidationProvider) {

    open val signUpButtonVisibility =
        ObservableInt(if (SignUpViewModelFactory.isInitialized()) View.VISIBLE else View.GONE)
    open val forgetPasswordButtonVisibility =
        ObservableInt(if (ForgetPasswordViewModelFactory.isInitialized()) View.VISIBLE else View.GONE)
    open var onSignUpClick: (() -> Unit)? = null
    open var onForgetPasswordClick: (() -> Unit)? = null

    abstract fun onLoginClick()

    override fun onCleared() {
        super.onCleared()
        onSignUpClick = null
        onForgetPasswordClick = null
    }
}