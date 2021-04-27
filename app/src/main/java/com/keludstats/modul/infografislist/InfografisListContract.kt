package com.keludstats.modul.infografislist

import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.showinfografis.ShowInfografisContract

interface InfografisListContract {
    interface View : ShowInfografisContract.View{
        fun redirectToNewInfografis()
        fun redirectToDetailInfografis(infografi: Infografi)
    }

    interface Presenter : ShowInfografisContract.Presenter{
    }

    interface Interactor {
    }
}