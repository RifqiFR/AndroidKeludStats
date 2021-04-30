package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

class IndikatorSatuan(
        @SerializedName("nama_tabel_indikator")
        var name: String,
        var satuan: String,
        @SerializedName("subindikator_id")
        var subindicatorId: Int
) {
    var id = -1
    @SerializedName("nilai_per_tahuns")
    var valuePerYear: Array<NilaiPerTahun> = emptyArray()
    val isValid get() = name.isNotBlank() && satuan.isNotBlank()

    companion object {
        const val API_PREFIX = "indikatorsatuan"
    }
}