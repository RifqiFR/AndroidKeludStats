package com.keludstats.modul.createinfografi

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.inputinfografi.InputInfografi
import com.keludstats.shared.modul.inputinfografi.InputInfografiActivity
import com.keludstats.shared.modul.inputinfografi.InputInfografiContract

interface CreateInfografiContract {
    interface View : InputInfografiContract.View {
        fun redirectToInfografiListAndRefreshList()
    }

    interface Presenter {
        fun createInfografi(inputInfografi: InputInfografi)
    }

    interface Interactor {
        fun requestCreateInfografi(inputInfografi: InputInfografi, callback: RequestCallback<Infografi>)
    }
}