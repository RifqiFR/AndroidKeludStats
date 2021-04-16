package com.keludstats.modul.newsubindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object NewSubindicatorInteractor: NewSubindicatorContract.Interactor {
    private val service = ServiceGenerator.createService(NewSubindicatorService::class.java)

    override fun requestCreateSubindicator(subindicator: Subindicator, callback: RequestCallback<Subindicator>) {
        service.createSubindicator(subindicator)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateSubindicator"))
    }
}