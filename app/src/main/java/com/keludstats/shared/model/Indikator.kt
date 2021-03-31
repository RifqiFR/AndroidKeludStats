package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

class Indikator(
    var id: Int,
    @SerializedName("nama_indikator")
    var indicatorName: String
)