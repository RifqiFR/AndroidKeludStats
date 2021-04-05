package com.keludstats.modul.table

import com.keludstats.shared.apiresponse.APIResponseCollection
import com.keludstats.shared.model.IndikatorSatuan
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface TableService {
    @GET("indikatorsatuan/subindikator/{id}")
    fun retrieveIndikatorSatuan(@Path("id") indikatorSatuanId: Int)
            : Call<APIResponseCollection<Array<IndikatorSatuan>>>
}