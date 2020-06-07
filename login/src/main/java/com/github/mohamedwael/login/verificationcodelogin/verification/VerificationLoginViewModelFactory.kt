package com.github.mohamedwael.login.verificationcodelogin.verification

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object VerificationLoginViewModelFactory : BaseLoginViewModelFactory() {

    lateinit var verificationConfig: VerificationConfig
    fun inject(
        verificationConfig: VerificationConfig,
        inputValidationProvider: InputValidationProvider
    ) {
        injectInputValidationProvider(inputValidationProvider)
        this.verificationConfig = verificationConfig
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (VerificationLoginViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                VerificationConfig::class.java,
                InputValidationProvider::class.java
            ).newInstance(verificationConfig, inputValidationProvider)
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}