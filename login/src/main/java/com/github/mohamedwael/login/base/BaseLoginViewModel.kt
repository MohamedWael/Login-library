package com.github.mohamedwael.login.base

import androidx.lifecycle.MutableLiveData
import com.blogspot.mowael.baselibrary.viewModel.ModernViewModel
import com.blogspot.mowael.utilslibrary.utils.SingleLiveDataEvent
import com.github.mohamedwael.login.config.InputValidationProvider

open class BaseLoginViewModel(val inputValidationProvider: InputValidationProvider) :
    ModernViewModel() {
    val hideKeyboardLiveData = MutableLiveData<SingleLiveDataEvent<Boolean>>()

    fun hideKeyboard() {
        hideKeyboardLiveData.value = SingleLiveDataEvent(true)
    }
}
