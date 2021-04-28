package com.keludstats.modul.updateinfografi

import android.util.Log
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.simple.pos.shared.extension.TAG

class UpdateInfografiPresenter(private val view: UpdateInfografiContract.View)
    : UpdateInfografiContract.Presenter
{
    private val interactor: UpdateInfografiContract.Interactor = UpdateInfografiInteractor()

    override fun updateInfografi(updateInfografi: UpdateInfografiModel) {
        if(!updateInfografi.isValid){
            view.showEveryFieldIsMandatoryErrorMessage()
            return
        }

        view.startLoading()
        interactor.requestUpdateInfografi(updateInfografi, object : RequestCallback<Infografi> {
            override fun requestSuccess(data: Infografi) {
                Log.d(TAG, "New Infografi : ${data.id}")
                view.stopLoading()
                view.redirectToDetailAndRefreshData(data)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }
}