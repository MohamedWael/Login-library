package com.github.mohamedwael.login.base

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object LoginBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: Int?) {
        if (errorMessage == null) {
            view.error = null
            return
        }
        if (errorMessage != 0) view.error = view.context.getString(errorMessage)
    }
}