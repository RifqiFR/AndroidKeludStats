package com.keludstats.modul.createinfografi

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.inputinfografi.InputInfografi
import com.simple.pos.shared.extension.TAG
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class CreateInfografiInteractor
    : BaseInteractor<CreateInfografiService>(CreateInfografiService::class.java)
        , CreateInfografiContract.Interactor {

    override fun requestCreateInfografi(inputInfografi: InputInfografi, callback: RequestCallback<Infografi>) {
        val title = inputInfografi.title.toRequestBody()
        val caption = inputInfografi.caption.toRequestBody()
        val date = inputInfografi.date.toRequestBody()
        val requestFile: RequestBody = inputInfografi.picture.asRequestBody()
        // MultipartBody.Part is used to send also the actual file name
        val picture = MultipartBody.Part.createFormData("gambar", inputInfografi.picture.name, requestFile)

        service.createInfografi(title, picture, caption, date)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateInfografi"))
    }
}