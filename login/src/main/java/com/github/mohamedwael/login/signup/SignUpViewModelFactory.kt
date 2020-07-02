package com.github.mohamedwael.login.signup

import androidx.lifecycle.ViewModel
import com.github.mohamedwael.login.base.BaseLoginViewModelFactory
import com.github.mohamedwael.login.config.InputValidationProvider

object SignUpViewModelFactory : BaseLoginViewModelFactory() {

    lateinit var signUp: SignUp

    fun init(signUp: SignUp, inputValidationProvider: InputValidationProvider) {
        injectInputValidationProvider(inputValidationProvider)
        this.signUp = signUp
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (SignUpViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                SignUp::class.java,
                InputValidationProvider::class.java
            ).newInstance(signUp, inputValidationProvider)
        } else {
            throw IllegalStateException("ViewModel must extend BaseLoginFragment")
        }
    }
}