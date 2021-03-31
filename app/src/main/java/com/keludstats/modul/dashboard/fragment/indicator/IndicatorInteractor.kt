package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object IndicatorInteractor: IndicatorContract.Interactor {
    private val service = ServiceGenerator.createService(IndicatorService::class.java)

    override fun requestRetrieveIndicators(requestCallback: RequestCallback<Array<Indikator>>) {
        service.retrieveIndicators()
                .enqueue(RetrofitCallback(
                        requestCallback, TAG, "requestRetrieveIndicators"
                ))
    }

    override fun requestRetrieveSubindicators(
            indikator: Indikator, requestCallback: RequestCallback<Array<Subindicator>>
    ) {
        service.retrieveSubindicators(indikator.id)
                .enqueue(RetrofitCallback(
                        requestCallback, TAG, "requestRetrieveSubindicators"
                ))
    }
}