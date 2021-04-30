package com.keludstats.modul.years

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Year

interface YearsContract {
    interface View {
        fun showYears(years: ArrayList<Year>)
        fun startLoading()
        fun stopLoading()
        fun removeYearFromList(year: Year)
        fun deleteYear(year: Year)
        fun showCreateYearDialog()
        fun redirectToIndikatorSatuansPage(year: Int)
    }

    interface Presenter {
        fun retrieveAndShowYears()
        fun deleteYear(year: Year)
    }

    interface Interactor {
        fun requestRetrieveYears(callback: RequestCallback<ArrayList<Year>>)
        fun requestDeleteYears(year: Year, callback: RequestCallback<Any?>)
    }
}