package com.github.mohamedwael.login.verificationcodelogin.usernamevalidation

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object UsernameValidationViewModelFactory : BaseLoginViewModelFactory() {

    private lateinit var verificationProvider: VerificationProvider
    fun inject(
        verificationProvider: VerificationProvider,
        inputValidationProvider: InputValidationProvider
    ) {
        injectInputValidationProvider(inputValidationProvider)
        this.verificationProvider = verificationProvider
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (UsernameValidationViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                VerificationProvider::class.java,
                InputValidationProvider::class.java
            ).newInstance(verificationProvider, inputValidationProvider)
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}