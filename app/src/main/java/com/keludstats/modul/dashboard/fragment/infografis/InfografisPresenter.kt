package com.keludstats.modul.dashboard.fragment.infografis

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.showinfografis.ShowInfografisInteractor

class InfografisPresenter(private val view: InfografisContract.View) : InfografisContract.Presenter {
    override fun showInfografis() {
        ShowInfografisInteractor.requestRetrieveInfografis(object : RequestCallback<ArrayList<Infografi>>{
            override fun requestSuccess(data: ArrayList<Infografi>) {
                //check if it's not empty
                if(data.isNotEmpty())
                    view.showInfografi(data[0])
                view.showInfografisPicture(data.toTypedArray())
            }

            override fun requestError(message: String?) {
                TODO("Not yet implemented")
            }

        })
    }
}
