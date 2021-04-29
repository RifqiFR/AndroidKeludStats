package com.keludstats.modul.editindicator

import com.keludstats.shared.model.Indikator
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Call

interface EditIndicatorService {
    @PUT(Indikator.API_PREFIX + "/{id}")
    fun editIndicator(
            @Path("id") indikatorId: Int, @Body indikator: Indikator
    ): Call<Indikator>
}