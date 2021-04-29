package com.keludstats.modul.editindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator

class EditIndicatorPresenter(private val view: EditIndicatorContract.View)
    : EditIndicatorContract.Presenter
{
    private val interactor: EditIndicatorContract.Interactor = EditIndicatorInteractor()

    override fun updateIndicator(indicator: Indikator) {
        if(indicator.indicatorName.isEmpty()){
            view.showNameCantBeEmptyError()
            return
        }

        view.startLoading()
        interactor.requestUpdateIndicator(indicator, object : RequestCallback<Indikator>{
            override fun requestSuccess(data: Indikator) {
                view.stopLoading()
                view.updateOldIndicator(data)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }
}