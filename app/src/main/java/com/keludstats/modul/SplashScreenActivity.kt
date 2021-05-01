package com.keludstats.modul

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.keludstats.R
import com.keludstats.base.util.UtilProvider
import com.keludstats.modul.dashboard.DashboardActivity
import com.keludstats.modul.onboarding.OnBoardingActivity
import com.keludstats.shared.util.OnBoardingUtil

class SplashScreenActivity : AppCompatActivity() {
    private val onBoardingUtil = UtilProvider.getUtil(OnBoardingUtil::class.java) as OnBoardingUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //if not first time opening app
        if(onBoardingUtil.sessionData)
            redirectToDashboardActivity()
        else
            redirectToOnBoardingActivity()
        finish()
    }

    private fun redirectToOnBoardingActivity() {
        startActivity(
                Intent(this, OnBoardingActivity::class.java)
        )
    }

    private fun redirectToDashboardActivity() {
        startActivity(
                Intent(this, DashboardActivity::class.java)
        )
    }
}