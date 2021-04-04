package com.keludstats.modul.login

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Token
import com.keludstats.shared.model.User

interface LoginContract {
    interface View {
        fun redirectToHome()
    }

    interface Presenter {
        fun authenticate(email: String, password: String)
        fun saveToken(apiToken: Token)
        fun saveUser(user: User)
    }

    interface Interactor {
        fun requestLogin(email: String, password: String, callback: RequestCallback<Token?>)
        fun addTokenToAllNextRequest(token: Token)
        fun requestSaveToken(token: Token)
        fun requestSaveUser(user: User?)
    }
}