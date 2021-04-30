package com.keludstats.modul.editsubindicator

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Subindicator

class EditSubindicatorPresenter(private val view: EditSubindicatorContract.View)
    : EditSubindicatorContract.Presenter
{
    private val interactor: EditSubindicatorContract.Interactor = EditSubindicatorInteractor()

    override fun updateSubindicator(subindicator: Subindicator) {
        if(subindicator.subindicatorName.isEmpty()){
            view.showFieldsCantBeEmptyError()
            return
        }

        view.startLoading()
        interactor.requestupdateSubindicator(subindicator, object : RequestCallback<Subindicator>{
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