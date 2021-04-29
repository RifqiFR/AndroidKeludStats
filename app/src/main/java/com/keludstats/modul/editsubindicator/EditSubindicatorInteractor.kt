package com.keludstats.modul.editsubindicator

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Subindicator
import com.simple.pos.shared.extension.TAG

class EditSubindicatorInteractor
    : BaseInteractor<EditSubindicatorService>(EditSubindicatorService::class.java)
    , EditSubindicatorContract.Interactor
{
    override fun requestupdateSubindicator(subindicator: Subindicator, callback: RequestCallback<Subindicator>) {
        service.updateSubindicator(subindicator.indicatorId, subindicator)
                .enqueue(RetrofitCallback(callback, TAG, "requestupdateSubindicator"))
    }
}