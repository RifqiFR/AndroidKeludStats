package com.keludstats.shared.extension

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.keludstats.R

fun Activity.showGlideImage(imageView: ImageView, pictureLink: String) {
    Glide.with(this)
            .load(pictureLink)
            .placeholder(R.drawable.loading_spinner)
            .into(imageView)
}