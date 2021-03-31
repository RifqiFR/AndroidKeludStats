package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

class Subindicator (
    var id: Int,
    @SerializedName("nama_subindikator")
    var subindicatorName: String,
    @SerializedName("indikator_id")
    var indicatorId: Int
)