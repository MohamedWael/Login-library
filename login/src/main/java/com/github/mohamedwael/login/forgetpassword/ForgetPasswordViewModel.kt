package com.github.mohamedwael.login.forgetpassword

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginViewModel
import com.github.mohamedwael.login.base.UsernameBaseViewModel
import com.github.mohamedwael.login.config.InputValidationProvider

class ForgetPasswordViewModel(inputValidationProvider: InputValidationProvider) :
    UsernameBaseViewModel(inputValidationProvider) {

    val forgetPasswordTitle = ObservableInt(R.string.forget_password)
    val forgetPasswordDescription = ObservableInt(R.string.forget_password_description)
    var onForgetPasswordClick: (() -> Unit)? = null
}
