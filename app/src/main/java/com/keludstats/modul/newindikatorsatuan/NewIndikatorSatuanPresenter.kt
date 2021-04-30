package com.keludstats.modul.newindikatorsatuan

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.IndikatorSatuan

class NewIndikatorSatuanPresenter(private val view: NewIndikatorSatuanContract.View)
    : NewIndikatorSatuanContract.Presenter
{
    private val interactor = NewIndikatorSatuanInteractor()

    override fun createIndikatorSatuan(indikatorSatuan: IndikatorSatuan) {
        if(!indikatorSatuan.isValid) {
            view.showFieldsCantBeEmptyError()
            return
        }
        view.startLoading()

        interactor.requestCreateIndikatorSatuan(
            indikatorSatuan, object : RequestCallback<IndikatorSatuan> {
                override fun requestSuccess(data: IndikatorSatuan) {
                    view.stopLoading()
                    view.updateList(data)
                }

                override fun requestError(message: String?) {
                    view.stopLoading()
                }
            }
        )
    }
}