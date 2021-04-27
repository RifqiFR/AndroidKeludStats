package com.keludstats.shared.modul.showinfografis

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.modul.dashboard.fragment.infografis.InfografisContract
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Infografi
import com.simple.pos.shared.extension.TAG

object ShowInfografisInteractor : BaseInteractor<ShowInfografisService>(
    ShowInfografisService::class.java), ShowInfografisContract.Interactor {

    override fun requestRetrieveInfografis(callback: RequestCallback<ArrayList<Infografi>>) {
        service.retrieveInfografis()
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveInfografis"))
    }
}