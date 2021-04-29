package com.keludstats.modul.createyear

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Year
import com.simple.pos.shared.extension.TAG

class CreateYearInteractor
    : BaseInteractor<CreateYearService>(CreateYearService::class.java)
        , CreateYearContract.Interactor
{
    override fun requestCreateYears(year: Year, callback: RequestCallback<Year>) {
        service.addYear(year)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateYears"))
    }
}