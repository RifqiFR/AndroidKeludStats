package com.keludstats.modul.newindikatorsatuan

import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface NewIndikatorSatuanContract {
    interface View : InputIndicatorContract.View {
         fun updateList(data: IndikatorSatuan)
    }

    interface Presenter {
        fun createIndikatorSatuan(indikatorSatuan: IndikatorSatuan)
    }

    interface Interactor {}
}