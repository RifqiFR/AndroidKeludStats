package com.keludstats.modul.updateinfografi

import com.keludstats.shared.model.Infografi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UpdateInfografiService {
    @Multipart
    @POST(Infografi.API_PREFIX + "/{id}?_method=PUT")
    fun updateInfografi(
            @Path("id") infografiId: Int,
            @Part("judul") title: RequestBody,
            @Part picture: MultipartBody.Part?,
            @Part("caption") caption: RequestBody,
            @Part("date") date: RequestBody
    ): Call<Infografi>
}