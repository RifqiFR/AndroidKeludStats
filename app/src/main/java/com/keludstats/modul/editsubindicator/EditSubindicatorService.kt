package com.keludstats.modul.editsubindicator

import com.keludstats.shared.model.Subindicator
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Call

interface EditSubindicatorService {
    @PUT(Subindicator.API_PREFIX + "/{id}")
    fun updateSubindicator(
            @Path("id") subindicatorId: Int, @Body subindicator: Subindicator
    ): Call<Subindicator>
}