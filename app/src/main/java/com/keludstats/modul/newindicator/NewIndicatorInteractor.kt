package com.keludstats.modul.newindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object NewIndicatorInteractor {
    private val service = ServiceGenerator.createService(NewIndicatorService::class.java)

    fun createIndicator(indicator: Indikator, callback: RequestCallback<Indikator>) {
        service.createStore(indicator)
            .enqueue(RetrofitCallback(callback, TAG, "createIndicator"))
    }
}