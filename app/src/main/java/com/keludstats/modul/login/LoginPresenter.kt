package com.keludstats.modul.login

import android.util.Log
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Token
import com.keludstats.shared.model.User
import com.keludstats.shared.singletondata.IsLoggedIn
import com.simple.pos.shared.extension.TAG

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {
    private val loginInteractor = LoginInteractor()

    override fun authenticate(email: String, password: String) {
        Log.d(TAG, "authenticate: { email: $email, password: $password }")
//        view.startLoading()
        loginInteractor.requestLogin(email, password, object : RequestCallback<Token?> {
            override fun requestSuccess(data: Token?) {
                saveToken(data!!)
                loginInteractor.addTokenToAllNextRequest(data)
                IsLoggedIn.isLoggedIn = true
                view.redirectToHome()
//                view.endLoading()
            }

            override fun requestError(message: String?) {
//                view.endLoading()
//                view.showError(message)
            }
        })
    }

    override fun saveToken(apiToken: Token) {
        Log.d(TAG, "saveToken: Save token to SharedPrefs")
        loginInteractor.requestSaveToken(apiToken)
    }

    override fun saveUser(user: User) {
        Log.d(TAG, "saveUser: Save user to SharedPrefs")
        loginInteractor.requestSaveUser(user)
    }
}