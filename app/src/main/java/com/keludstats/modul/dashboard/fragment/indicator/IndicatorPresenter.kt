package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator

class IndicatorPresenter(private val view: IndicatorContract.View): IndicatorContract.Presenter {
    override fun showIndicators() {
        IndicatorInteractor.requestRetrieveIndicators(object: RequestCallback<ArrayList<Indikator>>{
            override fun requestSuccess(data: ArrayList<Indikator>) {
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