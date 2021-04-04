package com.keludstats.modul.login

import com.keludstats.shared.apiresponse.APIResponseCollection
import com.keludstats.shared.model.Token
import com.keludstats.shared.model.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface LoginService {
    @POST("auth/login")
    fun login(@Body user: User?): Call<Token?>

    @POST("auth/refresh")
    fun refresh(): Call<Token?>
}