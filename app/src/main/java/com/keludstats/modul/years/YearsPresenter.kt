package com.keludstats.modul.years

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Year

class YearsPresenter(private val view: YearsContract.View) : YearsContract.Presenter {
    private val interactor : YearsContract.Interactor = YearsInteractor()

    override fun retrieveAndShowYears() {
        interactor.requestRetrieveYears(object : RequestCallback<ArrayList<Year>> {
            override fun requestSuccess(data: ArrayList<Year>) {
                view.showYears(data)
            }

            override fun requestError(message: String?) {
            }
        })
    }

    override fun deleteYear(year: Year) {
        view.startLoading()

        interactor.requestDeleteYears(year, object : RequestCallback<Any?> {
            override fun requestSuccess(data: Any?) {
                view.stopLoading()
                view.removeYearFromList(year)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }
}