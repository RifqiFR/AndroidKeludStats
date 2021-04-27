package com.keludstats.modul.dashboard.fragment.etc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keludstats.R
import com.keludstats.databinding.DashboardEtcFragmentBinding
import com.keludstats.modul.aboutapp.AboutAppActivity
import com.keludstats.modul.infografislist.InfografisListActivity
import com.keludstats.modul.login.LoginActivity

class EtcFragment: Fragment(), EtcContract.View {
    private lateinit var binding: DashboardEtcFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_etc_fragment, container, false)

        initializeOnClicks()
        return binding.root
    }

    private fun initializeOnClicks() {
        binding.apply {
            loginTv.setOnClickListener { redirectToLogin()}
            aboutTv.setOnClickListener { redirectToAboutApplication()}
            exitTv.setOnClickListener { exitApplication()}
            infografisBtn.setOnClickListener{ redirectToInfografis() }
        }
    }

    override fun exitApplication() {
        activity?.finish()
    }

    override fun redirectToAboutApplication() {
        context?.let{
            startActivity(Intent(
                it, AboutAppActivity::class.java
            ))
        }
    }

    override fun redirectToLogin() {
        startActivity(Intent(
                view?.context, LoginActivity::class.java
        ))
    }

    override fun redirectToInfografis() {
        startActivity(
                Intent(view?.context, InfografisListActivity::class.java)
        )
    }
}