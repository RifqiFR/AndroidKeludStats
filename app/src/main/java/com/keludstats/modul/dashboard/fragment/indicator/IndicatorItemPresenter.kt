package com.keludstats.modul.dashboard.fragment.indicator

import androidx.recyclerview.widget.RecyclerView
import com.keludstats.modul.dashboard.fragment.indicator.IndicatorContract
import com.keludstats.modul.dashboard.fragment.indicator.IndicatorInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator

class IndicatorItemPresenter(private val itemAdapter: IndicatorContract.ItemAdapter)
    : IndicatorContract.ItemPresenter {
    override fun showSubIndicator(indicator: Indikator, recyclerView: RecyclerView) {
        IndicatorInteractor.requestRetrieveSubindicators(indicator, object: RequestCallback<ArrayList<Subindicator>> {
            override fun requestSuccess(data: ArrayList<Subindicator>) {
                itemAdapter.showSubIndicator(data, recyclerView)
            }

            override fun requestError(message: String?) {
            }
        })
    }

    override fun deleteIndicator(indikator: Indikator) {
        IndicatorInteractor.requestDeleteIndicator(indikator, object: RequestCallback<Any?> {
            override fun requestSuccess(data: Any?) {
                itemAdapter.removeItem(indikator)
            }

            override fun requestError(message: String?) {
            }
        })
    }
}