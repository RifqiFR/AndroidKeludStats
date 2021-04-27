package com.keludstats.shared.modul.showinfografis

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi

interface ShowInfografisContract {
    interface View {
        fun showInfografis(infografi: ArrayList<Infografi>)
    }

    interface Presenter {
        fun showInfografis()
    }

    interface Interactor {
        fun requestRetrieveInfografis(callback: RequestCallback<ArrayList<Infografi>>)
    }
}