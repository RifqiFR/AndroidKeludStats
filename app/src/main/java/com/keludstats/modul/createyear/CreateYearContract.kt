package com.keludstats.modul.createyear

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Year
import com.keludstats.shared.modul.inputindicator.InputIndicatorContract

interface CreateYearContract {
    interface View : InputIndicatorContract.View {
        fun showCreateYearFailed()
        fun updateYearList(data: Year)
    }

    interface Presenter {
        fun createYear(year: Year)
    }

    interface Interactor {
        fun requestCreateYears(year: Year, callback: RequestCallback<Year>)
    }
}