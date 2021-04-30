package com.keludstats.modul.editindikatorsatuan

import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface EditIndikatorSatuanContract {
    interface View : InputIndicatorContract.View {
        fun updateList(indikatorSatuan: IndikatorSatuan)
    }

    interface Presenter
}