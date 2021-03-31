package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface IndicatorService {
    @GET("indikator")
    fun retrieveIndicators(): Call<Array<Indikator>>

    @GET("subindikator/indikator/{id}")
    fun retrieveSubindicators(@Path("id") indikatorId: Int): Call<Array<Subindicator>>
}