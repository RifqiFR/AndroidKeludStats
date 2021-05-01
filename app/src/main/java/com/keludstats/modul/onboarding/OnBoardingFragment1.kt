package com.keludstats.modul.onboarding

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.keludstats.R

class OnBoardingFragment1 : Fragment(R.layout.onboarding_pertama) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.skipBtn).setOnClickListener {
            (activity as OnBoardingActivity).redirectToDashboardActivity()
        }
    }
}