package com.github.mohamedwael.login.passwordlogin

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.LoginScenarioBaseViewModel
import com.github.mohamedwael.login.config.InputValidationProvider
import com.github.mohamedwael.login.verificationcodelogin.usernamevalidation.UsernameValidationViewModelFactory


open class PasswordLoginViewModel(
    inputValidationProvider: InputValidationProvider,
    private val passwordLogin: PasswordLogin
) : LoginScenarioBaseViewModel(inputValidationProvider) {

    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<Int?>()
    val verificationLoginButtonVisibility =
        ObservableInt(if (UsernameValidationViewModelFactory.isInitialized()) View.VISIBLE else View.GONE)
    var onVerificationLoginClick: (() -> Unit)? = null

    override fun onLoginClick() {
        hideKeyboard()
        if (isUsernameValid()) {
            userNameError.value = null
            validatePassword()
        } else {
            userNameError.value = R.string.username_not_valid
        }
    }

    private fun validatePassword() {
        val isPasswordValid = inputValidationProvider.isPasswordValid(password.value)
        if (isPasswordValid) {
            passwordError.value = null
            showProgressDialog(true)
            passwordLogin.login(
                userName.value!!,
                password.value!!,
                { showProgressDialog(false) },
                {
                    showProgressDialog(false)
                    showMessage(it)
                })
        } else {
            passwordError.value = R.string.password_not_valid
        }
    }
}
