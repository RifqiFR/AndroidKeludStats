package com.keludstats.modul.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.keludstats.R
import com.keludstats.databinding.LoginActivityBinding
import com.keludstats.shared.model.User

class LoginActivity: AppCompatActivity(), LoginContract.View {
    private val presenter = LoginPresenter(this)
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.user = User("", "")

        initializeButtons()
    }

    private fun initializeButtons(){
        binding.loginBtn.setOnClickListener {
            presenter.authenticate(binding.user!!.name, binding.user!!.password)
        }
    }

    override fun redirectToHome() {
        setResult(RESULT_OK)
        finish()
    }
}