package com.keludstats.shared.modul.showinfografis

import com.keludstats.shared.apiresponse.APIResponseCollection
import com.keludstats.shared.model.Infografi
import retrofit2.Call
import retrofit2.http.GET

interface ShowInfografisService {
    @GET(Infografi.API_PREFIX)
    fun retrieveInfografis(): Call<APIResponseCollection<ArrayList<Infografi>>>
}