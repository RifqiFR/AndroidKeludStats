package com.keludstats.base.modul

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.keludstats.R

abstract class BaseActivityWithActionBar() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_KeludStats_With_Toolbar)
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.action_bar)
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white)))
        }
    }
}