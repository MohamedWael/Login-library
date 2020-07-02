package com.github.mohamedwael.login.forgetpassword

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object ForgetPasswordViewModelFactory : BaseLoginViewModelFactory() {

    private lateinit var forgetPasswordConfig: ForgetPasswordConfig
    fun inject(
        forgetPasswordConfig: ForgetPasswordConfig,
        inputValidationProvider: InputValidationProvider
    ) {
        this.inputValidationProvider = inputValidationProvider
        this.forgetPasswordConfig = forgetPasswordConfig
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ForgetPasswordViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                ForgetPasswordConfig::class.java,
                InputValidationProvider::class.java
            ).newInstance(forgetPasswordConfig, inputValidationProvider)
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}