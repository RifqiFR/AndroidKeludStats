package com.keludstats.modul.editindikatorsatuan

import com.keludstats.shared.model.IndikatorSatuan
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Call

interface EditIndikatorSatuanService {
    @PUT(IndikatorSatuan.API_PREFIX + "/{id}")
    fun editIndikatorSatuan(
            @Path("id") subindikatorId: Int,
            @Body indikatorSatuan: IndikatorSatuan
    ) : Call<IndikatorSatuan>
}