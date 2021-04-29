package com.keludstats.modul.createyear

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Year

class CreateYearPresenter(private val view: CreateYearContract.View) : CreateYearContract.Presenter {
    private val interactor: CreateYearContract.Interactor = CreateYearInteractor()

    override fun createYear(year : Year) {
        view.startLoading()

        interactor.requestCreateYears(year, object : RequestCallback<Year> {
            override fun requestSuccess(data: Year) {
                view.stopLoading()
                view.updateYearList(data)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
                view.showCreateYearFailed()
            }
        })
    }
}