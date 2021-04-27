package com.keludstats.modul.infografislist

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.showinfografis.ShowInfografisInteractor

class InfografisListPresenter(private val view: InfografisListContract.View)
    : InfografisListContract.Presenter {
    override fun showInfografis() {
        ShowInfografisInteractor.requestRetrieveInfografis(object : RequestCallback<ArrayList<Infografi>>{
            override fun requestSuccess(data: ArrayList<Infografi>) {
                view.showInfografis(data)
            }

            override fun requestError(message: String?) {
                TODO("Not yet implemented")
            }

        })
    }
}