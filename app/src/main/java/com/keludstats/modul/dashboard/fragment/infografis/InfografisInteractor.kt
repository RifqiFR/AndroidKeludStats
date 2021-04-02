package com.keludstats.modul.dashboard.fragment.infografis

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object InfografisInteractor : InfografisContract.Interactor {
    private val service = ServiceGenerator.createService(InfografisService::class.java)

    override fun requestRetrieveInfografis(callback: RequestCallback<Array<Infografi>>) {
        service.retrieveInfografis()
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveInfografis"))
    }
}