package com.keludstats.modul.table

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.IndikatorSatuan

class TablePresenter(private val view: TableContract.View) : TableContract.Presenter {
    override fun retrieveTable(subIndicatorId: Int) {
        TableInteractor.requestRetrieveTable(
            subIndicatorId,
            object : RequestCallback<Array<IndikatorSatuan>> {
                override fun requestSuccess(data: Array<IndikatorSatuan>) {
                    view.showTable(data)
                }

                override fun requestError(message: String?) {
                    //TODO("Not yet implemented")
                }

            }
        )
    }
}