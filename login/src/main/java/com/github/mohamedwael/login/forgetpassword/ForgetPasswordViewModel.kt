package com.github.mohamedwael.login.forgetpassword

import com.github.mohamedwael.login.base.BaseLoginViewModel
import com.github.mohamedwael.login.config.InputValidationProvider

class ForgetPasswordViewModel(inputValidationProvider: InputValidationProvider) :
    BaseLoginViewModel(inputValidationProvider) {
    var onCreatePasswordClick: (() -> Unit)? = null
}
