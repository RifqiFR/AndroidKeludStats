package com.keludstats.modul.nilaipertahun

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.NilaiPerTahun

class NilaiPerTahunPresenter(private val view: NilaiPerTahunContract.View) : NilaiPerTahunContract.Presenter {
    override fun retrieveNilaiPerTahun(indikatorSatuanId: Int) {
        NilaiPerTahunInteractor.requestRetrieveNilaiPerTahun(
            indikatorSatuanId, object : RequestCallback<Array<NilaiPerTahun>> {
                override fun requestSuccess(data: Array<NilaiPerTahun>) {
                    view.showNilaiPerTahuns(data)
                }

                override fun requestError(message: String?) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}
