package com.keludstats.modul.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.keludstats.R
import com.keludstats.base.util.UtilProvider
import com.keludstats.modul.dashboard.DashboardActivity
import com.keludstats.shared.util.OnBoardingUtil

class OnBoardingActivity : AppCompatActivity(R.layout.onboarding_activity) {
    private val onBoardingUtil = UtilProvider.getUtil(OnBoardingUtil::class.java) as OnBoardingUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        val onBoardingFragments = arrayListOf(OnBoardingFragment1(), OnBoardingFragment2())

        super.onCreate(savedInstanceState)
        findViewById<ViewPager2>(R.id.onboadingVp).adapter =
                OnBoardingAdapter(this, onBoardingFragments)
    }

    fun redirectToDashboardActivity() {
        onBoardingUtil.initialize(true)
        startActivity(
                Intent(this, DashboardActivity::class.java)
        )
        finish()
    }
}