package com.keludstats.modul.years

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Year
import com.simple.pos.shared.extension.TAG

class YearsInteractor : BaseInteractor<YearsService>(YearsService::class.java)
        , YearsContract.Interactor{
    override fun requestRetrieveYears(callback : RequestCallback<ArrayList<Year>>) {
        service.retrieveYears()
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveYears"))
    }

    override fun requestDeleteYears(year: Year, callback: RequestCallback<Any?>) {
        service.deleteYear(year.tahun)
                .enqueue(RetrofitCallback(callback, TAG, "requestDeleteYears"))
    }
}