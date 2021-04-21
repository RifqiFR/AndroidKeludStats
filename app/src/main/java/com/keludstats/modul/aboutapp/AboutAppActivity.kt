package com.keludstats.modul.aboutapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keludstats.BuildConfig
import com.keludstats.R

class AboutAppActivity : AppCompatActivity(R.layout.about_application_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // show app's version
        findViewById<TextView>(R.id.aboutVersionTv).text =
            getString(R.string.version, BuildConfig.VERSION_NAME)
    }
}