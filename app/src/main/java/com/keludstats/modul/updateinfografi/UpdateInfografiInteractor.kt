package com.keludstats.modul.updateinfografi

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Infografi
import com.simple.pos.shared.extension.TAG
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class UpdateInfografiInteractor
    : BaseInteractor<UpdateInfografiService>(UpdateInfografiService::class.java),
        UpdateInfografiContract.Interactor {

    override fun requestUpdateInfografi(updateInfografi : UpdateInfografiModel, callback: RequestCallback<Infografi>) {
        val title = updateInfografi.title.toRequestBody()
        val caption = updateInfografi.caption.toRequestBody()
        val date = updateInfografi.date.toRequestBody()
        var picture : MultipartBody.Part? = null

        if(updateInfografi.picture.isFile){
            val requestFile: RequestBody = updateInfografi.picture.asRequestBody()
            // MultipartBody.Part is used to send also the actual file name
            picture = MultipartBody.Part.createFormData("gambar", updateInfografi.picture.name, requestFile)
        }

        service.updateInfografi(updateInfografi.id, title, picture, caption, date)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateInfografi"))
    }
}