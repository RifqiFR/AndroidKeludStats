package com.keludstats.modul.years

import com.keludstats.shared.model.Year
import retrofit2.Call
import retrofit2.http.*

interface YearsService {
    @GET(Year.API_PREFIX)
    fun retrieveYears() : Call<ArrayList<Year>>

    @DELETE(Year.API_PREFIX + "/{year}")
    fun deleteYear(@Path("year") year: Int) : Call<Any?>
}