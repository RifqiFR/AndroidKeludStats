package com.keludstats.modul.createnilaipertahun

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.NilaiPerTahun
import com.simple.pos.shared.extension.TAG

class CreateNilaiPerTahunInteractor
    : BaseInteractor<CreateNilaiPerTahunService>(CreateNilaiPerTahunService::class.java)
{
    fun requestCreateNilaiPerTahun(
            nilaiPerTahun: NilaiPerTahun,
            callback: RequestCallback<NilaiPerTahun>
    ) {
        service.createNilaiPerTahun(nilaiPerTahun)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateNilaiPerTahun"))
    }
}