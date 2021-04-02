package com.keludstats.shared.model

import com.google.gson.annotations.SerializedName

class Infografi(
        val id: Int,
        @SerializedName("judul")
        val title: String,
        @SerializedName("gambar")
        val pictureLink: String,
        val caption: String,
        val date: String
)