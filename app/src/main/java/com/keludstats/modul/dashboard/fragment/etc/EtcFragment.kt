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
            privacyTv.setOnClickListener { redirectToPrivacy()}
            notificationTv.setOnClickListener { redirectToNotification()}
            aboutTv.setOnClickListener { redirectToAboutApplication()}
            exitTv.setOnClickListener { exitApplication()}
        }
    }

    override fun exitApplication() {
        activity?.finish()
    }

    override fun redirectToAboutApplication() {
    }

    override fun redirectToNotification() {
    }

    override fun redirectToPrivacy() {
    }

    override fun redirectToLogin() {
        startActivity(Intent(
                view?.context, LoginActivity::class.java
        ))
    }
}