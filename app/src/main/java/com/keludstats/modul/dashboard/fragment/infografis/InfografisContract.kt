package com.keludstats.modul.dashboard.fragment.infografis

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi

interface InfografisContract {
    interface View {
        fun showInfografi(infografi: Infografi)
        fun showInfografisPicture(infografis: Array<Infografi>)
    }

    interface Presenter {
        fun showInfografis()
    }

    interface Interactor {
        fun requestRetrieveInfografis(callback: RequestCallback<Array<Infografi>>)
    }
}