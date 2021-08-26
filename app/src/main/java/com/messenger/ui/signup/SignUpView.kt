package com.messenger.ui.signup

import com.messenger.ui.BaseView
import com.messenger.ui.auth.AuthView

interface SignUpView : BaseView, AuthView {
    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun setPhoneNumberError()
    fun navigateToHome()
    fun showSignUpError()
}