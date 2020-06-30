package com.github.mohamedwael.login.base

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object LoginBindingAdapter {

//    @JvmStatic
//    @BindingAdapter("app:textUnderline")
//    fun textUnderline(textView: TextView, strRes: Int) {
//        val content = SpannableString(textView.context.getString(strRes))
//        content.setSpan(UnderlineSpan(), 0, content.length, 0)
//        textView.text = textView.context.getString(strRes)
//    }

//    @JvmStatic
//    @BindingAdapter("app:textUnderline")
    fun setUnderlinedText(textView: TextView, strRes: Int) {
        val underlineSpan = SpannableString(textView.context.getString(strRes))
        underlineSpan.setSpan(UnderlineSpan(), 0, underlineSpan.length, 0)
        textView.text = underlineSpan
    }

    @JvmStatic
    @BindingAdapter("app:textUnderline")
    fun setUnderlinedText(textView: TextView, text: String) {
        val underlineSpan = SpannableString(text)
        underlineSpan.setSpan(UnderlineSpan(), 0, underlineSpan.length, 0)
        textView.text = underlineSpan
    }

//    @JvmStatic
//    @BindingAdapter("app:textUnderline")
//    fun textUnderline(textView: TextView, text: String) {
//        val content = SpannableString(text)
//        content.setSpan(UnderlineSpan(), 0, content.length, 0)
//        textView.text = text
//    }


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