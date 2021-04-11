package com.keludstats.modul.newindicator

import com.keludstats.shared.model.Indikator
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface NewIndicatorService {
    @POST(Indikator.API_PREFIX)
    fun createStore(@Body indikator: Indikator): Call<Indikator>
}