package com.keludstats.modul.indikatorsatuans

import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun
import retrofit2.Call
import retrofit2.http.*

interface IndikatorSatuansService {
    @GET(IndikatorSatuan.API_PREFIX)
    fun retrieveIndikatorSatuan(
            @Query("subindikator") subindicatorId: Int,
            @Query("year") year: Int
    ): Call<ArrayList<IndikatorSatuan>>

    @DELETE(IndikatorSatuan.API_PREFIX + "/{id}")
    fun deleteIndikatorSatuan(
            @Path("id") indikatorSatuanId: Int
    ): Call<Any?>

    @POST(NilaiPerTahun.API_PREFIX)
    fun createNilaiPerTahun(
            @Body nilaiPerTahun: NilaiPerTahun
    ): Call<NilaiPerTahun>

    @DELETE(NilaiPerTahun.API_PREFIX + "/{year}/{indikatorSatuanId}")
    fun deleteNilaiPerTahun(
            @Path("year") year: Int,
            @Path("indikatorSatuanId") indikatorSatuanId: Int
    ): Call<Any?>
}