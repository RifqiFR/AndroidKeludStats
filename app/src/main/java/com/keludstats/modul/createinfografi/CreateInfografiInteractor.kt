package com.keludstats.modul.createinfografi

import com.keludstats.base.modul.BaseInteractor
import com.keludstats.shared.callback.RequestCallback
import com.keludstats.shared.callback.RetrofitCallback
import com.keludstats.shared.model.Infografi
import com.simple.pos.shared.extension.TAG
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class CreateInfografiInteractor
    : BaseInteractor<CreateInfografiService>(CreateInfografiService::class.java)
        , CreateInfografiContract.Interactor {

    override fun requestCreateInfografi(infografi: Infografi, callback: RequestCallback<Infografi>) {
        val title = infografi.title.toRequestBody()
        val caption = infografi.caption.toRequestBody()
        val date = infografi.date.toRequestBody()

        // use the FileUtils to get the actual file by uri
        val file = File(infografi.pictureLink)
        // create RequestBody instance from file
        val requestFile: RequestBody = file.asRequestBody()
        // MultipartBody.Part is used to send also the actual file name
        val picture = MultipartBody.Part.createFormData("gambar", file.name, requestFile)

        service.createInfografi(title, picture, caption, date)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateInfografi"))
    }
}