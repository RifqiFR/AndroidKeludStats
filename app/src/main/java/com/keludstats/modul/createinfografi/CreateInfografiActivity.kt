package com.keludstats.modul.createinfografi

import android.app.Activity
import android.os.Bundle
import com.keludstats.shared.modul.inputinfografi.InputInfografi
import com.keludstats.shared.modul.inputinfografi.InputInfografiActivity

class CreateInfografiActivity : InputInfografiActivity(), CreateInfografiContract.View {
    private val presenter : CreateInfografiContract.Presenter = CreateInfografiPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.inputInfografi = InputInfografi()
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        binding.uploadInfografiPhotoBtn.setOnClickListener {
            pickLogoFromGallery()
        }

        binding.saveInfografiBtn.setOnClickListener {
            binding.inputInfografi?.let {
                presenter.createInfografi(it)
            }
        }
    }

    override fun redirectToInfografiListAndRefreshList() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}