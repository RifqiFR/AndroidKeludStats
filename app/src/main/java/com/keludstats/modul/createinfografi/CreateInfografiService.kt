package com.keludstats.modul.createinfografi

import com.google.gson.annotations.SerializedName
import com.keludstats.shared.model.Infografi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CreateInfografiService {
    @Multipart
    @POST(Infografi.API_PREFIX)
    fun createInfografi(
            @Part("judul") title: RequestBody,
            @Part picture: MultipartBody.Part,
            @Part("caption") caption: RequestBody,
            @Part("date") date: RequestBody
    ): Call<Infografi>
}