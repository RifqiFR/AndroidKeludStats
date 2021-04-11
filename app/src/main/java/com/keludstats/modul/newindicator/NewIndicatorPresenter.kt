package com.keludstats.modul.newindicator

import android.util.Log
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.simple.pos.shared.extension.TAG

class NewIndicatorPresenter(private val view: NewIndicatorContract.View)
    : NewIndicatorContract.Presenter {

    override fun createIndicator(indicator: Indikator) {
        if(indicator.indicatorName.isEmpty()){
            view.showNameCantBeEmptyError()
            return
        }

        view.startLoading()
        NewIndicatorInteractor.createIndicator(indicator, object: RequestCallback<Indikator> {
            override fun requestSuccess(data: Indikator) {
                view.stopLoading()
                view.updateList(data)
            }

            override fun requestError(message: String?) {
                message?.let {
                    view.stopLoading()
                    Log.d(TAG, it)
                }
            }
        })
    }
}