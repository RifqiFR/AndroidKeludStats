package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

data class Indikator(
    @SerializedName("nama_indikator")
    var indicatorName: String
) {
    var id: Int = 0

    companion object {
        const val API_PREFIX = "indikator"
    }
}