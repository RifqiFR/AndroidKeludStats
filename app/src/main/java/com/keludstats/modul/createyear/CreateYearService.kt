package com.keludstats.modul.createyear

import com.keludstats.shared.model.Year
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateYearService {
    @POST(Year.API_PREFIX)
    fun addYear(@Body year: Year) : Call<Year>
}