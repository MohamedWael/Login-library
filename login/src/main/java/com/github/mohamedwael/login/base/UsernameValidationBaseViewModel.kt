package com.github.mohamedwael.login.base

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.config.InputValidationProvider
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordViewModelFactory
import com.github.mohamedwael.login.signup.SignUpViewModelFactory

abstract class UsernameValidationBaseViewModel(inputValidationProvider: InputValidationProvider) :
    BaseLoginViewModel(inputValidationProvider) {
    open val userName = MutableLiveData<String>()
    open val userNameError = MutableLiveData<Int?>()

    open val signUpButtonVisibility =
        ObservableInt(if (SignUpViewModelFactory.isInitialized()) View.VISIBLE else View.GONE)
    open val forgetPasswordButtonVisibility =
        ObservableInt(if (ForgetPasswordViewModelFactory.isInitialized()) View.VISIBLE else View.GONE)
    open var onSignUpClick: (() -> Unit)? = null
    open var onForgetPasswordClick: (() -> Unit)? = null

    open fun isUsernameValid() = inputValidationProvider.isEmailValid(userName.value)
            || inputValidationProvider.isUsernameValid(userName.value)

    abstract fun onLoginClick()
}