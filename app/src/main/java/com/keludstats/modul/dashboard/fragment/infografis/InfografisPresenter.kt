package com.keludstats.modul.dashboard.fragment.infografis

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi

class InfografisPresenter(private val view: InfografisContract.View) : InfografisContract.Presenter {
    override fun showInfografis() {
        InfografisInteractor.requestRetrieveInfografis(object : RequestCallback<Array<Infografi>>{
            override fun requestSuccess(data: Array<Infografi>) {
                //check if it's not empty
                if(data.isNotEmpty())
                    view.showInfografi(data[0])
                view.showInfografisPicture(data)
            }

            override fun requestError(message: String?) {
                TODO("Not yet implemented")
            }

        })
    }
}
