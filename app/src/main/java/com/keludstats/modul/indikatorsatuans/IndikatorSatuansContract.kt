package com.keludstats.modul.indikatorsatuans

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.IndikatorSatuan
import com.keludstats.shared.model.NilaiPerTahun

interface IndikatorSatuansContract {
    interface View {
        fun showIndikatorSatuans(data: ArrayList<IndikatorSatuan>)
        fun showAddIndikatorSatuanDialog()
        fun deleteNilaiPerTahun(nilaiPerTahun: NilaiPerTahun)
        fun startLoading()
        fun stopLoading()
        fun deleteNilaiPerTahunFromList(nilaiPerTahun: NilaiPerTahun)
        fun showUpdateIndikatorSatuanDialog(indikatorSatuan: IndikatorSatuan)
        fun showCreateNilaiPerTahun(indikatorSatuanId: Int)
        fun deleteIndikatorSatuan(indikatorSatuan: IndikatorSatuan)
        fun removeIndikatorSatuanFromList(indikatorSatuan: IndikatorSatuan)
    }

    interface Presenter {
        fun deleteNilaiPerTahun(nilaiPerTahun: NilaiPerTahun)
        fun retrieveAndShowIndikatorSatuans(subindicatorId: Int, year: Int)
        fun deleteIndikatorSatuan(indikatorSatuan: IndikatorSatuan)
    }

    interface Interactor {
        fun requestRetrieveIndikatorSatuans(subindicatorId: Int, year: Int, callback: RequestCallback<ArrayList<IndikatorSatuan>>)
        fun requestDeleteNilaiPerTahun(nilaiPerTahun: NilaiPerTahun, callback: RequestCallback<Any?>)
        fun requestCreateNilaiPerTahun(nilaiPerTahun: NilaiPerTahun, callback: RequestCallback<NilaiPerTahun>)
        fun requestDeleteIndikatorSatuan(indikatorSatuan: IndikatorSatuan, requestCallback: RequestCallback<Any?>)
    }
}