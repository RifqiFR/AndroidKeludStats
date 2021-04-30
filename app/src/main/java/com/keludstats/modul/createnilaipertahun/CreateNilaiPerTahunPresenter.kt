package com.keludstats.modul.createnilaipertahun

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.NilaiPerTahun

class CreateNilaiPerTahunPresenter(private val view: CreateNilaiPerTahunContract.View)
    : CreateNilaiPerTahunContract.Presenter
{
    private val interactor = CreateNilaiPerTahunInteractor()

    fun createNilaiPerTahun(nilaiPerTahun: NilaiPerTahun) {
        view.startLoading()

        interactor.requestCreateNilaiPerTahun(nilaiPerTahun, object : RequestCallback<NilaiPerTahun>{
            override fun requestSuccess(data: NilaiPerTahun) {
                view.stopLoading()
                view.updateNilaiPerTahun(data)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }
}