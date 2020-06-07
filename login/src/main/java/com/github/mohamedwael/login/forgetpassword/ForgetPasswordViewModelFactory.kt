package com.github.mohamedwael.login.forgetpassword

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object ForgetPasswordViewModelFactory : BaseLoginViewModelFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ForgetPasswordViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                InputValidationProvider::class.java
            ).newInstance(inputValidationProvider)
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}