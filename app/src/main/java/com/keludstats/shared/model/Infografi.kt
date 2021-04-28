package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.DateFormatSymbols
import java.util.*

class Infografi : Serializable {
        var id: Int = 0
        @SerializedName("judul")
        var title: String = ""
        @SerializedName("gambar")
        var pictureLink: String = ""
        var caption: String = ""
        var date: String = ""
        //if no property is just empty string return true
        val isValid : Boolean get() = !(
                title.isBlank() || pictureLink.isBlank() || caption.isBlank() || date.isBlank()
                )

        // date format is yyyy-mm-dd
        val dayDate get() = date.substring(date.length - 2, date.length)
        val monthDate get(): String {
                val monthDate = date.substring(date.length - 5, date.length - 3)
                val id = Locale("in", "ID") //translate month to indonesian

                return DateFormatSymbols(id).months[monthDate.toInt()]
        }

        companion object {
                const val API_PREFIX = "infografi"
        }
}