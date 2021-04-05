package com.keludstats.modul.table

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.IndikatorSatuan

interface TableContract {
    interface View {
        fun showTable(data: Array<IndikatorSatuan>)
        fun redirectToNilaiPerTahun(id: Int)
    }

    interface Presenter {
        fun retrieveTable(subIndicatorId: Int)
    }

    interface Interactor {
        fun requestRetrieveTable(
            subIndikatorId: Int,
            callback: RequestCallback<Array<IndikatorSatuan>>
        )
    }
}