package com.keludstats.modul.dashboard.fragment.indicator

import androidx.recyclerview.widget.RecyclerView
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator

interface IndicatorContract {
    interface View {
        fun showIndicators(indicators: Array<Indikator>)
        fun showErrorMessage(message: String)
        fun redirectToTable(subIndicatorId: Int)
        fun showNewSubIndicatorDialog(indikatorId: Int)
    }

    interface ItemAdapter {
        fun showSubIndicator(subindicators: Array<Subindicator>, recyclerView: RecyclerView)
    }

    interface ItemPresenter {
        fun showSubIndicator(indicator: Indikator, recyclerView: RecyclerView)
    }

    interface Presenter {
        fun showIndicators()
    }

    interface Interactor {
        fun requestRetrieveIndicators(requestCallback: RequestCallback<Array<Indikator>>)
        fun requestRetrieveSubindicators(indikator: Indikator, requestCallback: RequestCallback<Array<Subindicator>>)
    }
}