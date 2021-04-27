package com.keludstats.modul.infografislist

import com.keludstats.shared.modul.showinfografis.ShowInfografisContract

interface InfografisListContract {
    interface View : ShowInfografisContract.View{
        fun redirectToNewInfografis()
    }

    interface Presenter : ShowInfografisContract.Presenter{
    }

    interface Interactor {
    }
}