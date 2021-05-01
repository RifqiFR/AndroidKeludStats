package com.keludstats.modul.dashboard

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.keludstats.R
import com.keludstats.databinding.DashboardActivityBinding
import com.keludstats.modul.dashboard.fragment.etc.EtcFragment
import com.keludstats.modul.dashboard.fragment.indicator.IndicatorFragment
import com.keludstats.modul.dashboard.fragment.infografis.InfografisFragment

class DashboardActivity: AppCompatActivity(), DashboardContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: DashboardActivityBinding
    private var indicatorFragment: IndicatorFragment? = null
    private var infografisFragment: InfografisFragment? = null
    private var etcFragment: EtcFragment? = null
    private lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard_activity)

        binding.dashboardBottomNv.setOnNavigationItemSelectedListener(this)
        binding.dashboardBottomNv.selectedItemId = R.id.homeDashboardMenuItem
    }

    override fun changePageToHome() {
        if(infografisFragment == null)
            infografisFragment = InfografisFragment()

        selectedFragment = infografisFragment!!
    }

    override fun changePageToIndicator() {
        if(indicatorFragment == null)
            indicatorFragment = IndicatorFragment()

        selectedFragment = indicatorFragment!!
    }

    override fun changePageToEtc() {
        if(etcFragment == null)
            etcFragment = EtcFragment()

        selectedFragment = etcFragment!!
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