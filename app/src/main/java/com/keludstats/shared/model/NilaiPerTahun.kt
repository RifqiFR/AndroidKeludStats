package com.keludstats.shared.model

class NilaiPerTahun(var tahun: Int, var nilai: Float, var indikator_satuan_id: Int) {
    companion object {
        const val API_PREFIX = "nilaipertahun"
    }
}