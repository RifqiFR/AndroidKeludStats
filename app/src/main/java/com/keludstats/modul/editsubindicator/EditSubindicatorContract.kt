package com.keludstats.modul.editsubindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface EditSubindicatorContract {
    interface View : InputIndicatorContract.View {
        fun updateList(data : Subindicator)
    }

    interface Presenter {
        fun updateSubindicator(subindicator: Subindicator)
    }

    interface Interactor {
        fun requestupdateSubindicator(subindicator: Subindicator, callback: RequestCallback<Subindicator>)
    }
}