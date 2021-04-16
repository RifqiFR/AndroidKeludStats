package com.keludstats.modul.newsubindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator

interface NewSubindicatorContract {
    interface View {
        fun startLoading()
        fun showNameCantBeEmptyError()
        fun stopLoading()
        fun updateList(subindicator: Subindicator)
    }

    interface Presenter {
        fun createSubindicator(subindicator: Subindicator)
    }

    interface Interactor {
        fun requestCreateSubindicator(subindicator: Subindicator, callback: RequestCallback<Subindicator>)
    }
}