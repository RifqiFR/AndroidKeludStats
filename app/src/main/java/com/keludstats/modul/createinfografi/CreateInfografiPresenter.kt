package com.keludstats.modul.createinfografi

import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.inputinfografi.InputInfografi
import java.text.SimpleDateFormat
import java.util.*

class CreateInfografiPresenter(private val view: CreateInfografiContract.View)
    : CreateInfografiContract.Presenter {
    private val interactor : CreateInfografiContract.Interactor = CreateInfografiInteractor()

    override fun createInfografi(inputInfografi: InputInfografi) {
        inputInfografi.date = SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)

        if(!inputInfografi.isValid){
            view.showEveryFieldIsMandatoryErrorMessage()
            return
        }

        view.startLoading()
        interactor.requestCreateInfografi(inputInfografi, object : RequestCallback<Infografi> {
            override fun requestSuccess(data: Infografi) {
                view.stopLoading()
                view.redirectToInfografiListAndRefreshList()
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }

        })
    }
}