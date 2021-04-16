package com.keludstats.modul.newsubindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Subindicator

class NewSubindicatorPresenter(private val view: NewSubindicatorContract.View)
    : NewSubindicatorContract.Presenter
{
    override fun createSubindicator(subindicator: Subindicator) {
        if(subindicator.subindicatorName.isEmpty()){
            view.showNameCantBeEmptyError()
            return
        }

        view.startLoading()

        NewSubindicatorInteractor
                .requestCreateSubindicator(subindicator, object : RequestCallback<Subindicator> {
                    override fun requestSuccess(data: Subindicator) {
                        view.stopLoading()
                        view.updateList(data)
                    }

                    override fun requestError(message: String?) {
                        view.stopLoading()
                    }
                })
    }
}