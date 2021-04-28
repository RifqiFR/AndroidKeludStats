package com.keludstats.modul.updateinfografi

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.inputinfografi.InputInfografiContract

interface UpdateInfografiContract {
    interface View : InputInfografiContract.View {
        fun showOriginalImage()
        fun redirectToDetailAndRefreshData(newInfografi: Infografi)
    }

    interface Presenter {
        fun updateInfografi(updateInfografi: UpdateInfografiModel)
    }

    interface Interactor {
        fun requestUpdateInfografi(updateInfografi: UpdateInfografiModel, callback: RequestCallback<Infografi>)
    }
}