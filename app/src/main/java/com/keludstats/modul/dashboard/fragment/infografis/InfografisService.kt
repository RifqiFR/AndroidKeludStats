package com.keludstats.modul.dashboard.fragment.infografis

import com.keludstats.shared.apiresponse.APIResponseCollection
import com.keludstats.shared.model.Infografi
import retrofit2.http.GET
import retrofit2.Call

interface InfografisService {
    @GET("infografi")
    fun retrieveInfografis(): Call<APIResponseCollection<Array<Infografi>>>
}