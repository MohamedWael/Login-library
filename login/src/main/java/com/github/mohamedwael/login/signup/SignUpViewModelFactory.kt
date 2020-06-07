package com.github.mohamedwael.login.signup

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object SignUpViewModelFactory : BaseLoginViewModelFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (SignUpViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                InputValidationProvider::class.java
            )
                .newInstance(
                    inputValidationProvider
                )
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}