package com.keludstats.modul.createinfografi

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi

interface CreateInfografiContract {
    interface View {
        fun showEveryFieldIsMandatoryErrorMessage()
        fun startLoading()
        fun stopLoading()
        fun redirectToInfografiListAndRefreshList()
        fun pickLogoFromGallery()
    }

    interface Presenter {
        fun createInfografi(infografi: Infografi)
    }

    interface Interactor {
        fun requestCreateInfografi(infografi: Infografi, callback: RequestCallback<Infografi>)
    }
}