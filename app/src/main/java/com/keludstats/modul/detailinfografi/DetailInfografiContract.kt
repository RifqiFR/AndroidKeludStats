package com.keludstats.modul.detailinfografi

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi

interface DetailInfografiContract {
    interface View {
        fun startLoading()
        fun stopLoading()
        fun redirectToInfografisListAndRefreshList()
        fun redirectToUpdateInfografi()
    }

    interface Presenter {
        fun deleteInfografi(infografi: Infografi)
    }

    interface Interactor {
        fun deleteInfografi(infografi: Infografi, callback: RequestCallback<Unit?>)
    }
}