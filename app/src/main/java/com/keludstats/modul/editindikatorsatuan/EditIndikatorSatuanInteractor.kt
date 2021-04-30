package com.keludstats.modul.editindikatorsatuan

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.IndikatorSatuan
import com.simple.pos.shared.extension.TAG

class EditIndikatorSatuanInteractor
    : BaseInteractor<EditIndikatorSatuanService>(EditIndikatorSatuanService::class.java)
{
    fun requestUpdateIndikatorSatuan(
            indikatorSatuan: IndikatorSatuan,
            callback: RequestCallback<IndikatorSatuan>
    ) {
        service.editIndikatorSatuan(indikatorSatuan.id, indikatorSatuan)
                .enqueue(RetrofitCallback(callback, TAG, "requestUpdateIndikatorSatuan"))
    }
}