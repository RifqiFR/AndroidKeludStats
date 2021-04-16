package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

class Subindicator (
    @SerializedName("nama_subindikator")
    var subindicatorName: String,
    @SerializedName("indikator_id")
    var indicatorId: Int
) {
    var id: Int = 0

    companion object {
        const val API_PREFIX = "subindikator"
    }
}