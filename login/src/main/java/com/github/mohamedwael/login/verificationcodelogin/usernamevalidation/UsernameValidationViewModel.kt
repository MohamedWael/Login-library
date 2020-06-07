package com.github.mohamedwael.login.verificationcodelogin.usernamevalidation

import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.LoginScenarioBaseViewModel
import com.github.mohamedwael.login.config.InputValidationProvider

class UsernameValidationViewModel(
    private val verificationProvider: VerificationProvider,
    inputValidationProvider: InputValidationProvider
) : LoginScenarioBaseViewModel(inputValidationProvider) {

    lateinit var onVerificationCodeSent: (username: String) -> Unit
    override fun onLoginClick() {
        hideKeyboard()
        if (isUsernameValid()) {
            userNameError.value = null
            sendVerificationCode()
        } else {
            userNameError.value = R.string.username_not_valid
        }
    }

    private fun sendVerificationCode() {
        showProgressDialog(true)
        verificationProvider.sendVerificationCode(userName.value!!, {
            showProgressDialog(false)
            onVerificationCodeSent(userName.value!!)
        }, {
            showProgressDialog(false)
            showMessage(it)
        })
    }
}
