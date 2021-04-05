package com.keludstats.modul.table

import com.keludstats.shared.apiresponse.APIResponseCollection
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object TableInteractor : TableContract.Interactor{
    private val service = ServiceGenerator.createService(TableService::class.java)

    override fun requestRetrieveTable(
            subIndikatorId: Int,
            callback: RequestCallback<Array<IndikatorSatuan>>
    ) {
        service.retrieveIndikatorSatuan(subIndikatorId)
                .enqueue(RetrofitCallback(callback, TAG, "requestRetriveTable"))
    }
}