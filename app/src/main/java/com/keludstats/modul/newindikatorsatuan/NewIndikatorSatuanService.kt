package com.keludstats.modul.newindikatorsatuan

import com.keludstats.shared.model.IndikatorSatuan
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface NewIndikatorSatuanService {
    @POST(IndikatorSatuan.API_PREFIX)
    fun createIndikatorSatuan(@Body indikatorSatuan: IndikatorSatuan): Call<IndikatorSatuan>
}