package com.keludstats.modul.dashboard.fragment.infografis

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.showinfografis.ShowInfografisContract

interface InfografisContract {
    interface View {
        fun showInfografi(infografi: Infografi)
        fun showInfografisPicture(infografis: Array<Infografi>)
        fun redirectToDetailInfografi(infografi: Infografi)
    }

    interface Presenter {
        fun showInfografis()
    }
}