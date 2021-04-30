package com.keludstats.modul.editindikatorsatuan

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.IndikatorSatuan

class EditIndikatorSatuanPresenter(private val view: EditIndikatorSatuanContract.View)
    : EditIndikatorSatuanContract.Presenter
{
    private val interactor = EditIndikatorSatuanInteractor()

    fun updateIndicator(indikatorSatuan: IndikatorSatuan) {
        if(!indikatorSatuan.isValid) {
            view.showFieldsCantBeEmptyError()
            return
        }

        view.startLoading()
        interactor.requestUpdateIndikatorSatuan(
                indikatorSatuan,
                object : RequestCallback<IndikatorSatuan>{
                    override fun requestSuccess(data: IndikatorSatuan) {
                        view.updateList(data)
                        view.stopLoading()
                    }

                    override fun requestError(message: String?) {
                        view.stopLoading()
                    }
                }
        )
    }
}