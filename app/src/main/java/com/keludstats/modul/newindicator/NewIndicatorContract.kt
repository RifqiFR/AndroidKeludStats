package com.keludstats.modul.newindicator

import com.keludstats.shared.model.Indikator
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface NewIndicatorContract {
    interface View : InputIndicatorContract.View {
        fun updateList(indicator: Indikator)
    }

    interface Presenter {
        fun createIndicator(indicator: Indikator)
    }

    interface Interactor {
    }
}