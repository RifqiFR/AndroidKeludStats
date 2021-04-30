package com.keludstats.modul.createnilaipertahun

import com.keludstats.shared.model.NilaiPerTahun
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface CreateNilaiPerTahunContract {
    interface View : InputIndicatorContract.View {
        fun updateNilaiPerTahun(data: NilaiPerTahun)
    }

    interface Presenter
}