package com.github.mohamedwael.login.signup

import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.base.BaseLoginViewModel
import com.github.mohamedwael.login.config.InputValidationProvider

open class SignUpViewModel (inputValidationProvider: InputValidationProvider) : BaseLoginViewModel(inputValidationProvider) {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun onTermsClick() {}
    fun onSignUpClick() {}
}
