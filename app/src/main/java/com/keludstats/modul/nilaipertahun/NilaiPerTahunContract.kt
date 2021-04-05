package com.keludstats.modul.nilaipertahun

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.NilaiPerTahun

interface NilaiPerTahunContract {
    interface View {
        fun showNilaiPerTahuns(data: Array<NilaiPerTahun>)
    }

    interface Presenter {
        fun retrieveNilaiPerTahun(indikatorSatuanId: Int)
    }

    interface Interactor {
        fun requestRetrieveNilaiPerTahun(
            indikatorSatuanId: Int,
            callback: RequestCallback<Array<NilaiPerTahun>>
        )
    }
}