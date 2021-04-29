package com.keludstats.modul.editindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface EditIndicatorContract {
    interface View : InputIndicatorContract.View {
        fun updateOldIndicator(indicator: Indikator)
    }

    interface Presenter {
        fun updateIndicator(indicator: Indikator)
    }

    interface Interactor {
        fun requestUpdateIndicator(indicator: Indikator, callback: RequestCallback<Indikator>)
    }
}