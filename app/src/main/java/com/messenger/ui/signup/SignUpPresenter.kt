package com.messenger.ui.signup

import com.messenger.data.local.AppPreferences

interface SignUpPresenter {
    var preferences: AppPreferences
    fun executeSignUp(username: String, phoneNumber: String, password: String)
}