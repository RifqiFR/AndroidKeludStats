package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

class IndikatorSatuan(
        @SerializedName("nama_tabel_indikator")
        var name: String,
        var satuan: String,
        @SerializedName("nilai_per_tahun")
        var valuePerYear: Array<NilaiPerTahun>
) {
    var id = -1
}