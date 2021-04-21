package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName
import java.text.DateFormatSymbols

class Infografi(
        val id: Int,
        @SerializedName("judul")
        val title: String,
        @SerializedName("gambar")
        val pictureLink: String,
        val caption: String,
        val date: String
) {
        // date format is yyyy-mm-dd
        val dayDate get() = date.substring(date.length - 2, date.length)
        val monthDate get(): String {
                val monthDate = date.substring(date.length - 5, date.length - 3)

                return DateFormatSymbols().months[monthDate.toInt()]
        }
}