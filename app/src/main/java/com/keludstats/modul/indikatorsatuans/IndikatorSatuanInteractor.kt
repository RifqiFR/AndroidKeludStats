package com.keludstats.modul.indikatorsatuans

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun
import com.simple.pos.shared.extension.TAG

class IndikatorSatuanInteractor
    : BaseInteractor<IndikatorSatuansService>(IndikatorSatuansService::class.java)
        , IndikatorSatuansContract.Interactor
{
    override fun requestRetrieveIndikatorSatuans(
            subindicatorId: Int,
            year: Int,
            callback: RequestCallback<ArrayList<IndikatorSatuan>>
    ) {
        service.retrieveIndikatorSatuan(subindicatorId, year)
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveIndikatorSatuans"))
    }

    override fun requestDeleteNilaiPerTahun(nilaiPerTahun: NilaiPerTahun, callback: RequestCallback<Any?>) {
        service.deleteNilaiPerTahun(nilaiPerTahun.tahun, nilaiPerTahun.indikator_satuan_id)
                .enqueue(RetrofitCallback(callback, TAG, "requestDeleteNilaiPerTahun"))
    }

    override fun requestCreateNilaiPerTahun(nilaiPerTahun: NilaiPerTahun, callback: RequestCallback<NilaiPerTahun>) {
        service.createNilaiPerTahun(nilaiPerTahun)
                .enqueue(RetrofitCallback(callback, TAG, "requestDeleteNilaiPerTahun"))
    }

    override fun requestDeleteIndikatorSatuan(
            indikatorSatuan: IndikatorSatuan,
            requestCallback: RequestCallback<Any?>
    ) {
        service.deleteIndikatorSatuan(indikatorSatuan.id)
                .enqueue(RetrofitCallback(requestCallback, TAG, "requestDeleteIndikatorSatuan"))
    }
}