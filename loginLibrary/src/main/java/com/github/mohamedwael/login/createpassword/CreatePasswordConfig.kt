package com.github.mohamedwael.login.createpassword

import android.view.View
import androidx.databinding.ObservableInt

data class CreatePasswordConfig(
    val onPasswordMatch: (password: String, confirmPassword: String, isPasswordMatches: Boolean) -> Unit,
    val onSendActionClick: (() -> Unit)? = null,
    val sendButtonVisibility: ObservableInt = ObservableInt(View.VISIBLE),
    val confirmPasswordVisibility: ObservableInt = ObservableInt(View.VISIBLE)
)