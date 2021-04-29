package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object IndicatorInteractor: BaseInteractor<IndicatorService>(IndicatorService::class.java)
        ,IndicatorContract.Interactor
{
    override fun requestRetrieveIndicators(requestCallback: RequestCallback<ArrayList<Indikator>>) {
        service.retrieveIndicators()
                .enqueue(RetrofitCallback(
                        requestCallback, TAG, "requestRetrieveIndicators"
                ))
    }

    override fun requestDeleteIndicator(indikator: Indikator, callback: RequestCallback<Any?>) {
        service.deleteIndicator(indikator.id)
                .enqueue(RetrofitCallback(callback, TAG, "requestDeleteIndicators"))
    }

    override fun requestRetrieveSubindicators(
            indikator: Indikator, requestCallback: RequestCallback<ArrayList<Subindicator>>
    ) {
        service.retrieveSubindicators(indikator.id)
                .enqueue(RetrofitCallback(
                        requestCallback, TAG, "requestRetrieveSubindicators"
                ))
    }

    override fun requestDeleteSubindicators(subindicator: Subindicator, callback: RequestCallback<Any?>) {
        service.deleteSubindicator(subindicator.id)
                .enqueue(RetrofitCallback(callback, TAG, "requestDeleteSubindicators"))
    }
}