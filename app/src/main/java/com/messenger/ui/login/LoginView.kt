package com.messenger.ui.login

import com.messenger.ui.BaseView
import com.messenger.ui.auth.AuthView

interface LoginView: BaseView, AuthView {
    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToSignUp()
    fun navigateToHome()
}