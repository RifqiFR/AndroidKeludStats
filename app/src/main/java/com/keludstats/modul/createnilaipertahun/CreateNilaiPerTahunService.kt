package com.keludstats.modul.createnilaipertahun

import retrofit2.Call
import com.keludstats.shared.model.NilaiPerTahun
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateNilaiPerTahunService {
    @POST(NilaiPerTahun.API_PREFIX)
    fun createNilaiPerTahun(@Body nilaiPerTahun: NilaiPerTahun): Call<NilaiPerTahun>
}