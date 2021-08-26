package com.messenger.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.messenger.R
import com.messenger.data.local.AppPreferences
import com.messenger.ui.main.MainActivity

class LoginActivity:AppCompatActivity(),LoginView, View.OnClickListener {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var preferences: AppPreferences
    private lateinit var presenter: LoginPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        presenter = LoginPresenterImpl(this)

        bingViews()
    }

    override fun bingViews() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignUn = findViewById(R.id.btn_sign_up)
        progressBar =findViewById(R.id.progress_bar)
        btnLogin.setOnClickListener(this)
        btnSignUn.setOnClickListener(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun showAuthError() {
        Toast.makeText(this, "Invalid username and password combination",
            Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setUsernameError() {
        etUsername.error = "Username field cannot be empty"
    }

    override fun setPasswordError() {
        etPassword.error = "Password field cannot be empty"
    }

    override fun navigateToSignUp() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun navigateToHome() {
        finish()
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_login) {
            presenter.executeLogin(etUsername.text.toString(),etPassword.text.toString())
        } else if ( view.id == R.id.btn_sign_up) {
            navigateToSignUp()
        }
    }

}