package com.keludstats.modul.editindicator

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Indikator
import com.simple.pos.shared.extension.TAG

class EditIndicatorInteractor
    : BaseInteractor<EditIndicatorService>(EditIndicatorService::class.java)
    , EditIndicatorContract.Interactor
{
    override fun requestUpdateIndicator(indicator: Indikator, callback: RequestCallback<Indikator>) {
        service.editIndicator(indicator.id, indicator)
                .enqueue(RetrofitCallback(callback, TAG, "requestUpdateIndicator"))
    }
}