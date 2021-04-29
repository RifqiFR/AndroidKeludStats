package com.keludstats.modul.dashboard.fragment.indicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Subindicator

class IndicatorSubItemPresenter(private val subItemAdapter : IndicatorSubItemRecyclerAdapter)
    : IndicatorContract.SubItemPresenter
{
    override fun deleteSubindicator(subindicator: Subindicator) {
        IndicatorInteractor.requestDeleteSubindicators(subindicator, object : RequestCallback<Any?> {
            override fun requestSuccess(data: Any?) {
                subItemAdapter.removeItem(subindicator)
            }

            override fun requestError(message: String?) {
            }
        })
    }
}