package com.keludstats.modul.nilaipertahun

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.NilaiPerTahun
import com.keludstats.shared.retrofit.ServiceGenerator
import com.simple.pos.shared.extension.TAG

object NilaiPerTahunInteractor: NilaiPerTahunContract.Interactor {
    private val service = ServiceGenerator.createService(NilaiPerTahunService::class.java)

    override fun requestRetrieveNilaiPerTahun(
        indikatorSatuanId: Int, callback: RequestCallback<Array<NilaiPerTahun>>
    ) {
        service.retrieveNilaiPerTahun(indikatorSatuanId)
            .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveNilaiPerTahun"))
    }
}