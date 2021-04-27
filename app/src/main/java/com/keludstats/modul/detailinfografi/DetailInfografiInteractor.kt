package com.keludstats.modul.detailinfografi

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Infografi
import com.simple.pos.shared.extension.TAG

class DetailInfografiInteractor
    : BaseInteractor<DetailInfografiService>(DetailInfografiService::class.java)
        , DetailInfografiContract.Interactor {

    override fun deleteInfografi(infografi: Infografi, callback: RequestCallback<Unit?>) {
        service.deleteInfografi(infografi.id)
                .enqueue(RetrofitCallback(callback, TAG, "deleteInfografi"))
    }
}