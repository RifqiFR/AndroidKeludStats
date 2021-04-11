package com.keludstats.modul.newindicator

import com.keludstats.shared.model.Indikator

interface NewIndicatorContract {
    interface View {
        fun updateList(indicator: Indikator)
        fun stopLoading()
        fun startLoading()
        fun showNameCantBeEmptyError()
    }

    interface Presenter {
        fun createIndicator(indicator: Indikator)
    }

    interface Interactor {
    }
}