package com.keludstats.modul.newsubindicator

import com.keludstats.shared.model.Subindicator
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NewSubindicatorService {
    @POST(Subindicator.API_PREFIX)
    fun createSubindicator(@Body subindicator: Subindicator): Call<Subindicator>
}