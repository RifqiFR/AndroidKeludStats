package com.keludstats.modul.dashboard.fragment.indicator

import androidx.recyclerview.widget.RecyclerView
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator

interface IndicatorContract {
    interface View {
        fun showErrorMessage(message: String)
        fun redirectToTable(subIndicatorId: Int)
        fun showNewSubIndicatorDialog(indikatorId: Int)
        fun showIndicators(indicators: ArrayList<Indikator>)
        fun showEditIndicatorDialog(indicator: Indikator)
        fun showEditSubindicatorDialog(subindicator: Subindicator)
    }

    interface ItemAdapter {
        fun showSubIndicator(subindicators: ArrayList<Subindicator>, recyclerView: RecyclerView)
        fun removeItem(indikator: Indikator)
        fun showdeleteIndicatorWarning(viewHolder: IndicatorItemRecyclerAdapter.MyViewHolder, indikator: Indikator)
    }

    interface ItemPresenter {
        fun showSubIndicator(indicator: Indikator, recyclerView: RecyclerView)
        fun deleteIndicator(indikator: Indikator)
    }

    interface Presenter {
        fun showIndicators()
    }

    interface Interactor {
        fun requestRetrieveIndicators(requestCallback: RequestCallback<ArrayList<Indikator>>)
        fun requestRetrieveSubindicators(indikator: Indikator, requestCallback: RequestCallback<ArrayList<Subindicator>>)
        fun requestDeleteIndicator(indikator: Indikator, callback: RequestCallback<Any?>)
        fun requestDeleteSubindicators(subindicator: Subindicator, callback: RequestCallback<Any?>)
    }

    interface SubItemPresenter {
        fun deleteSubindicator(subindicator: Subindicator)
    }

    interface SubitemAdapter {
        fun showdeleteSubindicatorWarning(viewHolder: IndicatorSubItemRecyclerAdapter.MyViewHolder, subindicator: Subindicator)
    }
}