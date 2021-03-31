package com.keludstats.modul.dashboard

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.keludstats.R
import com.keludstats.databinding.DashboardActivityBinding
import com.keludstats.modul.dashboard.fragment.indicator.IndicatorFragment

class DashboardActivity: AppCompatActivity(), DashboardContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: DashboardActivityBinding
    private var indicatorFragment: IndicatorFragment? = null
    private lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard_activity)
        binding.dashboardBottomNv.setOnNavigationItemSelectedListener(this)
        binding.dashboardBottomNv.selectedItemId = R.id.indikatorDashboardMenuItem
    }

    override fun changePageToHome() {

    }

    override fun changePageToIndicator() {
        if(indicatorFragment == null)
            indicatorFragment =
                IndicatorFragment()

        selectedFragment = indicatorFragment!!
    }

    override fun changePageToEtc() {

    }

    private fun showChangedPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.dashboardPageFl, selectedFragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.homeDashboardMenuItem -> changePageToHome()
            R.id.indikatorDashboardMenuItem -> changePageToIndicator()
            R.id.etcDashboardMenuItem -> changePageToEtc()
        }

        showChangedPage()
        return true
    }
}