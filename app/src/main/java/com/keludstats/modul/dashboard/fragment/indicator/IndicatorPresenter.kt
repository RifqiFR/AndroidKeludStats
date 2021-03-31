package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator

class IndicatorPresenter(private val view: IndicatorContract.View): IndicatorContract.Presenter {
    override fun showIndicators() {
        IndicatorInteractor.requestRetrieveIndicators(object: RequestCallback<Array<Indikator>>{
            override fun requestSuccess(data: Array<Indikator>) {
                view.showIndicators(data)
            }

            override fun requestError(message: String?) {
                message?.let{
                    view.showErrorMessage(it)
                }
            }
        })
    }
}