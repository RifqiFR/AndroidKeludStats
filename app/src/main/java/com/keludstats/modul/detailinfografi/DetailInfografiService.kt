package com.keludstats.modul.detailinfografi

import com.keludstats.shared.model.Infografi
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.Call

interface DetailInfografiService {
    @DELETE(Infografi.API_PREFIX + "/{id}")
    fun deleteInfografi(@Path("id") infografiId : Int): Call<Unit?>
}