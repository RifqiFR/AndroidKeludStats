package com.keludstats.modul.detailinfografi

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi

class DetailInfografiPresenter(private val view: DetailInfografiContract.View)
    : DetailInfografiContract.Presenter {
    private val interactor : DetailInfografiContract.Interactor = DetailInfografiInteractor()

    override fun deleteInfografi(infografi: Infografi) {
        view.startLoading()

        interactor.deleteInfografi(infografi, object : RequestCallback<Unit?> {
            override fun requestSuccess(data: Unit?) {
                view.redirectToInfografisListAndRefreshList()
                view.stopLoading()
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }
}