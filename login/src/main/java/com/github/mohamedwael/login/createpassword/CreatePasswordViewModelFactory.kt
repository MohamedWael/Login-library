package com.github.mohamedwael.login.createpassword

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object CreatePasswordViewModelFactory : BaseLoginViewModelFactory() {

    lateinit var config: CreatePasswordConfig

    fun inject(config: CreatePasswordConfig, inputValidationProvider: InputValidationProvider) {
        injectInputValidationProvider(inputValidationProvider)
        this.config = config
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (CreatePasswordViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                CreatePasswordConfig::class.java,
                InputValidationProvider::class.java
            ).newInstance(config, inputValidationProvider)
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}