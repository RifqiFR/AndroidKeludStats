package com.keludstats.modul.indikatorsatuans

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun

class IndikatorSatuansPresenter(private val view: IndikatorSatuansContract.View)
    : IndikatorSatuansContract.Presenter
{
    private val interactor: IndikatorSatuansContract.Interactor = IndikatorSatuanInteractor()

    override fun retrieveAndShowIndikatorSatuans(
            subindicatorId : Int,
            year: Int
    ) {
        interactor.requestRetrieveIndikatorSatuans(
                subindicatorId,
                year,
                object : RequestCallback<ArrayList<IndikatorSatuan>> {
                    override fun requestSuccess(data: ArrayList<IndikatorSatuan>) {
                        view.showIndikatorSatuans(data)
                    }

                    override fun requestError(message: String?) {
                    }
                }
        )
    }

    override fun deleteIndikatorSatuan(indikatorSatuan: IndikatorSatuan) {
        view.startLoading()

        interactor.requestDeleteIndikatorSatuan(
                indikatorSatuan,
                object : RequestCallback<Any?>
                {
                    override fun requestSuccess(data: Any?) {
                        view.stopLoading()
                        view.removeIndikatorSatuanFromList(indikatorSatuan)
                    }

                    override fun requestError(message: String?) {
                        view.stopLoading()
                    }
                }
        )
    }

    override fun deleteNilaiPerTahun(nilaiPerTahun: NilaiPerTahun) {
        view.startLoading()

        interactor.requestDeleteNilaiPerTahun(nilaiPerTahun, object : RequestCallback<Any?>{
            override fun requestSuccess(data: Any?) {
                view.stopLoading()
                view.deleteNilaiPerTahunFromList(nilaiPerTahun)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }
}