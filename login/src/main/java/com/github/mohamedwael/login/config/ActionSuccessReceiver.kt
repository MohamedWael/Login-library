package com.github.mohamedwael.login.config

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.io.Serializable

const val BROADCAST_ACTION_ACTION_SUCCESS = "com.github.mohamedwael.login.success"
const val BROADCAST_SUCCESS_ACTION_DATA = "com.github.mohamedwael.login.success.data"

abstract class ActionSuccessReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        onSuccess(context, intent?.extras?.getSerializable(BROADCAST_SUCCESS_ACTION_DATA))
    }

    abstract fun onSuccess(context: Context?, response: Serializable?)

    fun getPasswordLoginIntentFilter() = IntentFilter(BROADCAST_ACTION_ACTION_SUCCESS)
}