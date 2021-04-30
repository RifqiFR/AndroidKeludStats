package com.keludstats.modul.newindikatorsatuan

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.IndikatorSatuan
import com.simple.pos.shared.extension.TAG

class NewIndikatorSatuanInteractor
    : BaseInteractor<NewIndikatorSatuanService>(NewIndikatorSatuanService::class.java)
{
    fun requestCreateIndikatorSatuan(
            indikatorSatuan: IndikatorSatuan, callback: RequestCallback<IndikatorSatuan>
    ) {
        service.createIndikatorSatuan(indikatorSatuan)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateIndikatorSatuan"))
    }
}