package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface IndicatorService {
    @GET(Indikator.API_PREFIX)
    fun retrieveIndicators(): Call<ArrayList<Indikator>>

    @DELETE(Indikator.API_PREFIX + "/{id}")
    fun deleteIndicator(@Path("id") indikatorId: Int): Call<Any?>

    @GET("${Subindicator.API_PREFIX}/${Indikator.API_PREFIX}/{id}")
    fun retrieveSubindicators(@Path("id") indikatorId: Int): Call<ArrayList<Subindicator>>

    @DELETE(Subindicator.API_PREFIX + "/{id}")
    fun deleteSubindicator(@Path("id") subindikatorId: Int): Call<Any?>
}