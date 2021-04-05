package com.keludstats.modul.nilaipertahun

import com.keludstats.shared.model.NilaiPerTahun
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface NilaiPerTahunService {
    @GET("nilaipertahun/indikatorsatuan/{id}")
    fun retrieveNilaiPerTahun(@Path("id") indikatorSatuanId: Int)
            : Call<Array<NilaiPerTahun>>
}
